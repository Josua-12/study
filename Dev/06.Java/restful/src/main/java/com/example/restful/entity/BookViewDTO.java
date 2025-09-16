package com.example.restful.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Schema(description = "책 정보 응답 DTO")
public class BookViewDTO {
    @Schema(description = "책 고유 ID", example = "1")
    @JsonProperty("bookId")  //JSON 키 이름 커스터마이징 (선택사항)
    private Long id;

    @Schema(description = "책 제목", example = "Spring 고수 가이드")
    private String suject;

    @Schema(description = "가격(원)", example = "35000", minimum = "0")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)   //숫자형식 보장
    private int price;

    @Schema(description = "저자명", example = "이개발")
    private String author;

    @Schema(description = "총 페이지 수", example = "450", minimum = "1")
    private int page;

    @Schema(
            description = "생성일시",
            example = "2025-09-12T12:31:50",
            type = "string",
            format = "date-time"
    )
    @JsonFormat(
           shape = JsonFormat.Shape.STRING,     //문자열로 변환
           pattern = "yyyy-MM-dd HH:mm:ss",     //포맷 지정
           timezone = "Asia/Seoul"              //시간대 설정
    )
    private LocalDateTime createdAt;

}
