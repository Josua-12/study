package com.library.dto.board;

/*
    BoardCreateDTO와의 차이점
        - 기존 파일 삭제를 위한 deleteFileIds 필드 추가
        - 게시글 ID는 URL 파라미터로 전달되므로 DTO에 포함하지 않음
    사용 흐름
        - 사용자가 수정 폼에서 입력
        - BoardUpdateDTO (데이터 바인딩)
        - Validation 검증 (@Valid)
        - BoardService.updateBoard()
        - Board Entity의 update() 메서드 호출 (더티체킹)
        - DB 자동 update
 */

import com.library.entity.board.BoardCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardUpdateDTO {
    @NotBlank(message = "제목은 필수 입력 항목입니다.")
    @Size(max = 200, message = "제목은 200자를 초과할 수 없습니다.")
    private String title;

    @NotBlank(message = "내용은 필수 입력 항목입니다.")
    private String content;

    @NotNull(message = "카테고리는 필수 선택 항목입니다.")
    private BoardCategory category;

    //새로 추가할 파일 목록
    private List<MultipartFile> files = new ArrayList<>();

    //삭제할 기존 파일 ID 목록
    private List<Long> deleteFileIds = new ArrayList<>();
}
