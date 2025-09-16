package com.example.restful.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
/*
    DTO를 별도로 만든 이유
        - Entity를 직접 반환하면 양방향 연관 관계로
          불필요한 내부 정부가 노출될 수 있음
        - DTO를 사용하면 클라이언트에게 필요한 정보만 선택적으로 제공할 수 있음
        - 날짜 포맷이나 계산된 필드 같은 추가 정보도 쉽게 포함시킬 수 있음
        - 유지보수 용이함
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(description = "책 정보 요청 DTO")
public class BookPayloadDTO {   // DTO(Data Transfer Object) - 클라이언트 <--> 서버간 데이터 전송용 객체
    @NotBlank   // Bean Validation: null, "", " " 모두 허용 안함 (문자열 전용 검증)
    @Schema(
            description = "책 제목",
            example = "Java의 고수",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String subject;
    @NotNull
    @Min(value = 0, message = "가격은 0원 이상이어야 합니다.")
    @Schema(
            description = "책 가격",
            example = "25000",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private int price;
    @NotBlank
    @Schema(
            description = "저자 이름",
            example = "이순신",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String author;
    @NotNull
    @Min(value = 1, message = "페이지는 1 이상이어야 합니다.")
    @Schema(
            description = "총 페이지 수",
            example = "350",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private Integer page;
}
