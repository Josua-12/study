package com.library.service;

/*
    CommentService 단위 테스트
        - Mockito를 사용한 단위 테스트
        - 실제 DB나 Spring context 없이 Service 로직만 테스트
        - Repository를 Mock으로 대체하여 빠른 실행

        - 댓글 생성 성공 케이스
        - 게시글이 없을 때 예외 발생
 */

import com.library.dto.board.CommentCreateDTO;
import com.library.dto.board.CommentDTO;
import com.library.entity.board.Board;
import com.library.entity.board.Comment;
import com.library.entity.member.Member;
import com.library.repository.BoardRepository;
import com.library.repository.CommentRepository;
import com.library.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)     // Mockito 사용을 위한 Junit 5 확장
public class CommentServiceTest {

    //Mock 객체 (실제 구현에 대신 가짜 객체 주입)
    @Mock
    private CommentRepository commentRepository;
    @Mock
    private BoardRepository boardRepository;
    @Mock
    private MemberRepository memberRepository;

    @InjectMocks        // 테스트 대상: Mock 객체들이 주입된 실제 Service
    private CommentService commentService;

    /*
        댓글 생성 정상 케이스 테스트
            - 게시글과 작성자가 존재할 때 댓글이 정상 생성되는가?
            - 생성된 댓글의 내용이 올바른지?
            - Repository의 save() 메서드가 정확히 1번 호출되는가?
     */
    @Test
    void createComment_정상작성() {
        //Given: 테스트 데이터 준비

        Long boardId = 1L;                      //게시글 ID
        String loginId = "test@library.com";    //로그인 사용자 이메일
        // 댓글 생성 요청 DTO
        CommentCreateDTO dto = CommentCreateDTO.builder()
                .content("테스트 댓글")          //댓글 내용
                .build();
        //Mock 게시글 엔티티 생성
        Board board = Board.builder()
                .id(boardId)
                .build();
        //Mock 작성자 엔티티 생성
        Member author = Member.builder()
                .email(loginId)
                .name("이순신")
                .build();
        //Mock Comment 엔티티 생성
        Comment comment = createCommentWithDates(
                1L,                         // 댓글 ID
                "테스트 댓글",                // 댓글 내용
                board,                      // 게시글
                author,                     // 작성자
                LocalDateTime.now(),        // 생성일시
                LocalDateTime.now()         // 수정일시
        );

        // Mock 동작 정의
        // boardRepository.findById()가 호출되면 board를 반환
        when(boardRepository.findById(boardId)).thenReturn(Optional.of(board));
        // memberRepository.findByEmail()가 호출되면 author를 반환
        when(memberRepository.findByEmail(loginId)).thenReturn(Optional.of(author));
        // commentRepository.save()가 호출되면 comment를 반환
        when(commentRepository.save(any(Comment.class))).thenReturn(comment);

        // When : 실제 테스트 대상 메서드 실행
        CommentDTO result = commentService.createComment(boardId,dto, loginId);

        // Then: 결과 검증
        // 반환된 DTO의 내용이 예상값과 일치하는지 확인
        assertThat(result.getContent()).isEqualTo("테스트 댓글");
        // Repository의 save() 메서드가 정확히 1번 호출되었는지 검증
        verify(commentRepository, times(1)).save(any(Comment.class));
    }

    private Comment createCommentWithDates(
            Long id,
            String content,
            Board board,
            Member author,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {

        //Builder로 Comment 기본 속성 설정
        Comment comment = Comment.builder()
                .id(id)
                .content(content)
                .board(board)
                .author(author)
                .build();

        //리플렉션으로 BaseEntity의 날짜 필드 설정
        setBaseEntityFields(comment, createdAt, updatedAt);

        return comment;

    }

    /*
        리플렉션을 사용하여 BaseEntity의 날짜 필드 설정
            - 리플렉션은 런타임에 클래스 구조를 조작할 수 있는 강력한 도구임
                - 런타임 시점에 클래스의 정보를 조회하고 조작
                - 컴파일 타임이 아닌 실행 시점에 동적으로 작동
                - private, protected 필드나 메서드에도 접근 가능
     */
    private void setBaseEntityFields(
            Comment comment,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {

        try {
            // createdAt 필드 설정
            java.lang.reflect.Field createdAtField =
            comment.getClass().getSuperclass().getDeclaredField("createdAt");
            createdAtField.setAccessible(true);     // private  필드 접근 허용
            createdAtField.set(comment, createdAt); // 값 설정

            // updatedAt 필드 설정
            java.lang.reflect.Field updateAtField =
                    comment.getClass().getSuperclass().getDeclaredField("updatedAt");
            updateAtField.setAccessible(true);     // private  필드 접근 허용
            updateAtField.set(comment, updatedAt); // 값 설정

        } catch (NoSuchFieldException e) {
            throw new RuntimeException("BaseEntity에 필드가 존재하지 않습니다: " + e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("BaseEntity에 필드가 존재하지 않습니다: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("BaseEntity 필드 설정 중 오류 발생 " , e);
        }


    }


}
