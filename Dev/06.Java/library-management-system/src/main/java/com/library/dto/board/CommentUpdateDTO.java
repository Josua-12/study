package com.library.dto.board;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

/*
    댓글 수정 DTO - 댓글 수정시 사용, 유효성 검증 포함
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentUpdateDTO {

    @NotBlank(message = "댓글 내용을 입력해주세요.")
    @Size(max = 1000, message = "댓글은 최대 1000자까지 입력 가능합니다.")
    private String content;
}
