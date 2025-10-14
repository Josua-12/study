package com.library.dto.board;

import com.library.entity.board.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/*
    게시글 상세 조회용 DTO
        - 게시글의 전체 내용을 포함함
        - 포함 정보
            - 게시글 상세 정보 : ID, 제목, 본문, 카테고리, 작성일시, 수정일시
            - 통계 정보 : 조회수, 좋아요, 댓글 수
            - 작성자 정보 : 이름, 이메일
*/
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDetailDTO {
    private Long id;
    private String title;
    private String content;
    private String authorName;
    private String authorEmail;
    private Long viewCount;
    private Long likeCount;
    private Integer commentCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /*
        Board Entity를 BoardDetailDTO로 변환하는 정적 메서드
            - Board Entity와 연관된 Member Entity의 정보를 함께 추출함
    */
    public static BoardDetailDTO from(Board board) {
        return BoardDetailDTO.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .authorName(board.getAuthor().getName())
                .authorEmail(board.getAuthor().getEmail())
                .viewCount(board.getViewCount())
                .likeCount(board.getLikeCount())
                .commentCount(0)
                .createdAt(board.getCreatedAt())
                .updatedAt(board.getUpdatedAt())
                .build();
    }

}
