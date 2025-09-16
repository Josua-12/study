package com.initializr.springstarter.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    //리뷰 일련번호 (예: 1, 2, 3, 4 ....자동증가)
    private String content;     //리뷰 내용
    private int rating;         //평점 (예: 1 ~ 5)
    private LocalDateTime createdAt;        // 작성일자
    @ManyToOne  // 다대일 관계 매핑 (여러 리뷰가 하나의 책에 속함)
    @JoinColumn(
        name = "book_id",      // 실제 데이터베이스 컬럼명 (외래키 컬럼)
        referencedColumnName =  "id",    // Book 테이블의 어떤 컬럼을 참조할지 지정(Book.id)
        nullable = false        // not null 제약조건
    )
    private Book book;      // Book 엔터티 참조 => book.getId()로 책ID 접근 가능
}
