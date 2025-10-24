package com.library.repository;

import com.library.entity.board.Board;
import com.library.entity.board.BoardStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/*
    게시글 Repository
        - Board Entity의 데이터베이스 접근을 담당
        - Spring Data JPA의 메서드 네이밍 규칙을 활용하여 쿼리를 자동 생성함
            - 메서드 네이밍 규칙
                - findBy : SELECT 쿼리 생성
                - And/Or : 조건 조합
                - OrderBy : 정렬 조건

 */
public interface BoardRepository extends JpaRepository<Board, Long> {

    /*
        게시글 상태별 목록 조회 (페이징)
            - 목록 상태(ACTIVE, DELETED, HIDDEN)의 게시글을 페이징하여 조회함
            - 생성된 쿼리 (기본)
                - SELECT * FROM board WHERE status = ?
                  ORDER BY ...
                  LIMIT ? OFFSET ?
     */



    /*
        게시글 상태별 목록 조회 (페이징, 작성자 정보 포함)
            - N+1 문제를 방지하기 위해 Fetch Join을 사용함
            - Fetch Join은 메서드 네이밍으로 표현할 수 없어 @Query로
              명시적 작성이 필요함
            - countQuery 분리 이유
                - 전체 개수 조회 시 JOIN 불필요 (성능 최적화)
                - Board 테이블만 COUNT하면 충분
                - ORDER BY도 불필요 (개수만 세면 됨)

        파라미터 바인딩:
            :status <=== 메서드의 BoardStatus status 파라미터와 자동 매칭

        @param status : 조회할 게시글 상태
        @param pageable : 페이징 정보 (LIMIT, OFFSET 자동 추가)
        @return 페이징된 게시글 목록 (작성자 정보 포함)
     */
    @Query(
            //데이터 조회 쿼리
            value = "SELECT b " +       // Board 엔티티 선택
                    "FROM Board b " +   // Board 엔티티에서
                    "JOIN FETCH b.author " +    // Member도 함께 로드 (N+1 방지)
                    "WHERE b.status = :status " +  // status가 일치하는 것만
                    "ORDER BY b.createdAt DESC",    // 최신순 정렬

            //개수 조회 쿼리 (페이징용)
            countQuery = "SELECT COUNT(b) " +       // Board 개수 세기
                        "FROM Board b " +           // Board 엔티티에서
                        "WHERE b.status = :status"  // status가 일치하는 것만
    )
    Page<Board> findByStatusWithAuthor(BoardStatus status, Pageable pageable);


    /*
        게시글 단건 조회 (ID + 상태, 작성자 정보 포함)
            - N + 1 문제를 방지하기 위해서 Fetch Join을 사용함
            - Active 상태의 게시글만 조회 (삭제된 글은 조회가 불가)

       @param id : 조회할 게시글 ID
       @param status : 조회할 게시글 상태 (일반적으로 ACTIVE)
     */
    @Query("SELECT b from Board b " +
            "JOIN FETCH b.author " +
            "WHERE b.id = :id  AND b.status = :status")
    Optional<Board> findByIdAndStatusWithAuthor(Long id, BoardStatus status);
}















