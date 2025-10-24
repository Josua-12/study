package com.library.comment;

import com.library.entity.board.Board;
import com.library.entity.board.Comment;
import com.library.entity.board.CommentStatus;
import com.library.entity.member.Member;
import com.library.entity.member.MemberStatus;
import com.library.entity.member.MemberType;
import com.library.entity.member.Role;
import com.library.repository.BoardRepository;
import com.library.repository.CommentRepository;
import com.library.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/*
    CommentRepository 테스트 클래스
        - 목적 : 댓글 리포지터리의 데이터베이스 조회 메서드들이 올바르게 동작하는지 검증
        - 테스트 항목
            - findByBoardIdAndStatus: 게시글 ID와 상태별 댓글 조회
            - countByBoardIdAndStatus: 게시글 ID와 상태별 댓글 갯수 조회
        - @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
            - application.yml에 설정된 DB를 무시하고,
              테스트용 embedded database로 교체함
            - 실제 DB 대신 H2 In-Memory DB를 사용하여 테스트 격리
            - 각 테스트 메서드 실행 후 트랜잭션 자동 롤백으로 테스트 간 데이터 독립성 보장
 */
@DataJpaTest // JPA 관련 컴포넌트만 로드하는 슬라이스 테스트 (Entity, Repository)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;    // 댓글 리파지토리
    @Autowired
    private BoardRepository boardRepository;    // 게시글 리파지토리 (댓글은 게시판에 종속)
    @Autowired
    private MemberRepository memberRepository;  // 회원 리파지토리 (댓글과 게시판은 작성자 필요)

    private Member testAuthor;                  // 테스트용 공통 데이터: 댓글 작성자.

    private Board testBoard;                    // 테스트용 공통 데이터: 댓글이 달린 게시글

    /*
        각 테스트 메서드 실행 전에 자동으로 실행되는 초기화 메서드
            - 목적: 모든 테스트가 동일한 기본 데이터를 시작하도록 보장
            - 실행 시점: @Test 어노테이션이 붙은 각 메서드 실행 직전
     */
    @BeforeEach
    void setUp() {
        // 1단계: 테스트용 회원 생성
        testAuthor = Member.builder()
                .name("신사임당")
                .email("test1@library.com")
                .password("password123")
                .phone("010-1234=5678")
                .address("서울시 마포구")
                .memberType(MemberType.REGULAR)
                .status(MemberStatus.ACTIVE)
                .role(Role.USER)
                .build();
        memberRepository.save(testAuthor);      // DB에 회원 저장

        // 2단계: 테스트용 게시글 생성 (댓글이 달릴 예정)
        testBoard = Board.builder()
                .title("테스트 게시글")
                .content("테스트 내용입니다.")
                .author(testAuthor)             // 작성자(위에서 생성한 회원 연결)
                .viewCount(0L)
                .build();
        boardRepository.save(testBoard);        // DB에 계시글 저장
    }


    /*
        특정 게시물의 활성 상태 댓글만 정확히 조회되는지 검증
            - 검증 사항
                - ACTIVE 상태의 댓글만 조회되는가?
                - DELETED 상태의 댓글은 조회되지 않는가?
                - 조회된 댓글의 내용과 상태가 올바른가?
        Given-When-Then 패턴 사용
            - Given : 테스트에 필요한 데이터 준비
            - When : 테스트 대상 메서드 실행
            - Then : 결과 검증
     */
    @Test
    void findByBoardIdAndStatus_활성댓글조회() {
         // Given: 테스트 데이터 준비 - 활성 댓글 1개, 삭제된 댓글 1개 생성
         // 활성 상태의 댓글 생성
        Comment activeComment = Comment.builder()
                .content("첫 번째 댓글")
                .board(testBoard)               //어떤 게시글에 달린 댓글인지
                .author(testAuthor)             //댓글 작성자
                .status(CommentStatus.ACTIVE)   //상태: 활성 (화면에 표시됨)
                .build();
        commentRepository.save(activeComment);  //DB에 저장

        // 삭제된 상태의 댓글 생성
        Comment deletedComment = Comment.builder()
                .content("두 번째 댓글")
                .board(testBoard)               //어떤 게시글에 달린 댓글인지
                .author(testAuthor)             //댓글 작성자
                .status(CommentStatus.DELETED)   //상태: 삭제됨 (화면에 표시 안됨)
                .build();
        commentRepository.save(deletedComment);  //DB에 저장

        // When : 실제 테스트 - 특정 게시글의 활성 댓글만 조회
        List<Comment> activeComments = commentRepository.findByBoardIdAndStatus(
                testBoard.getId(),          //조회할 게시글 Id
                CommentStatus.ACTIVE        //조회할 댓글 상태 (활성만)
        );

        // Then : 결과 검증 - 활성 댓글 1개만 조회되고, 내용이 올바른지 확인
        assertThat(activeComments).hasSize(1);      //조회된 댓글 개수가 1인지
        assertThat(activeComments.get(0).getContent()).isEqualTo("첫 번째 댓글");  //댓글 내용이 맞는지
        assertThat(activeComments.get(0).getStatus()).isEqualTo(CommentStatus.ACTIVE);   //상태가 ACTIVE인지
    }

    /*
        테스트 목적 : 특정 게시글의 활성 상태 댓글 갯수를 정확히 계산하는지 검증
            - 점검 사항
                - ACTIVE 상태의 댓글만 카운트되는가?
                - DELETED 상태의 댓글은 카운트에서 제외되는가?
                - 반환되는 개수가 정확한가?
     */
    @Test
    void countByBoardIdAndStatus_댓글개수조회() {
        // Given : 테스트 데이터 준비 - 활성 댓글 3개, 삭제된 댓글 1개 생성
        // 반복문으로 활성 댓글 3개 생성 (실제 여러 사용자가  댓글을 단 상황 시뮬레이션)
        for (int i=1; i <=3; i++) {
            Comment comment = Comment.builder()
                    .content("댓글 " + i)         // 댓글 내용 (댓글 1, 댓글 2, 댓글 3)
                    .board(testBoard)            // 같은 게시글에 달린 댓글들
                    .author(testAuthor)          // 같은 작성자
                    .status(CommentStatus.ACTIVE)   // 모두 활성 상태
                    .build();
            commentRepository.save(comment);        //DB에 저장
        }

        // 삭제된 댓글 1개 추가 (카운트에서 제외되어야 함)
        Comment deletedComment = Comment.builder()
                .content("삭제된 댓글")
                .board(testBoard)
                .author(testAuthor)
                .status(CommentStatus.DELETED)          // 상태: 삭제됨
                .build();
        commentRepository.save(deletedComment);         // DB에 저장

        // When : 실제 테스트 - 특정 게시물의 활성 댓글 개수 조회
        Long activeCount = commentRepository.countByBoardIdAndStatus(
                testBoard.getId(),                      // 조회할 게시글 ID
                CommentStatus.ACTIVE                    // 카운트할 댓글 상태 (활성만)
        );

        // Then : 결과 검증 - 활성 댓글 3개만 카운트되는지 확인 (삭제된 댓글 1개는 제외)
        assertThat(activeCount).isEqualTo(3L);  //개수가 정확히 3개인지
    }
}
