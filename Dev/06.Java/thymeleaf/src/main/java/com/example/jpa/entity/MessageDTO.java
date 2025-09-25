package com.example.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Locale;

/*
    메시지 데이터 전송 객체 (DTO: Data Transfer Object)
        - 클라이언트와 서버 간 메시지 데이터를 주고 받기 위한 객체
        - JSON <=> Java 객체 자동 변환용
 */
@Data       // Lombok: @Getter + @Setter + @ToString + @EqualsAndHashCode
@Builder    // 빌터 패전 자동 생성
@NoArgsConstructor      //기본 생성자
@AllArgsConstructor
public class MessageDTO {

    private Long id;            //메시지 고유 번호 (PK)
    private String title;       //메시지 제목
    private String content;
    private String author;
    private LocalDateTime createdAt;
    private boolean success;    // 처리 성공 여부
    private String message;     // 응답 메시지
}
