package com.library.dto.board;

/*
    게시글 목록 조회용 DTO
        - 전체 게시글 내용은 포함하지 않아 네트워크 전송량을 최적화함
        - 포함 정보
            - 게시글 기본 정보 : ID, 제목, 카테고리, 작성일시
            - 통계 정보 : 조회수, 좋아요 수, 댓글 수
            - 작성자 정보 : 이름 (Member Entity의 name)

        - from() 메서드
 */

import com.library.entity.board.Board;
import com.library.entity.board.BoardCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardListDTO {
    private Long id;
    private String title;
    private String authorName;
    private Long viewCount;
    private Long likeCount;
    private Integer commentCount;
    private BoardCategory category;
    private LocalDateTime createdAt;

    /*
        Board Entity를 BoardListDTO로 변환하는 정적 메서드
            - Board Entity와 연관된 Member Entity의 정보를 함께 추출함.
     */
    public static BoardListDTO from(Board board) {
        return BoardListDTO.builder()
                .id(board.getId())
                .title(board.getTitle())
                .authorName(board.getAuthor().getName())    // 실제 이름 사용(화면표시용)
                .viewCount(board.getViewCount())
                .likeCount(board.getLikeCount())
                .commentCount(0)        // 댓글 기능 구현시 실제 count로 반경
                .category(board.getCategory())
                .createdAt(board.getCreatedAt())
                .build();
    }
}
