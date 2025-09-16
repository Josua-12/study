package com.example.restful.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity     // JPA 엔터티임 선언 (데이터베이스 테이블과 매핑)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id //기본키 (PK) 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 자동 증가 전략 (auto_increment)
    private Long id;
    private String subject;
    private int price;
    private String author;
    private int page;
    private LocalDateTime createdAt;
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL) // book 필드로 매핑.
    private List<BookImage> bookImages;     // 객체 참조용 (DB 컬럼 아님, 관계 매핑을 위한  필드)

    @PrePersist     //엔터티가 처음 저장되기 전에 실행
    public void onCreate() {
        this.createdAt = LocalDateTime.now();       // 현재 시간으로 생성일시 설정
    }
}
