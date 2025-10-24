package com.library.dto.board;

/*
    댓글 조회 DTO
        - 댓글 목록 조회 시 사용
        - Entity -> DTO 변환 메서드 포함
 */

import com.library.entity.board.Comment;
import lombok.*;

import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDTO {
    private Long id;
    private String content;
    private String authorName;      //작성자 이름
    private String authorEmail;     //작성자 이메일 (권장 확인용)
    private String createAt;        //포맷팅된 작성일시
    private String updatedAt;       //포맷팅된 수정일시

    public static CommentDTO from(Comment comment) {
        // 날짜/시간 포맷터 생성 (예: "2025-10-21 16:28")
        // 패턴설명: yyyy(년 4자리) - MM(월 2자리) - dd(일 2자리) - HH (시 24시간) : mm (분)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        // 빌더 패턴으로 DTO 객체 생성 및  반환
        return CommentDTO.builder()
                .id(comment.getId())            // 댓글 ID (수정/삭제 시 식별용)
                .content(comment.getContent())  // 댓글 내용 (화면에 표시될 텍스트)
                .authorName(comment.getAuthor().getName())  //작성자 이름 (Member 엔티티에서 추출)
                .authorEmail(comment.getAuthor().getEmail()) // 작성자 이메일 (수정/삭제 시 권한 확인용)
                .createAt(comment.getCreatedAt().format(formatter))  //작성일시를 문자열로 포맷팅
                .updatedAt(comment.getUpdatedAt().format(formatter)) //수정일시를 문자열로 포맷팅
                .build();

    }

}

















