package com.example.restful.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int type;   //이미지 타입 (1: 쎔네일, 2: 일반 이미지)
    private String originalFileName;    //원본 파일명
    private String fileName;        // 저장된 파일명 (중복 방지를 위해 변경된 이름)
    @ManyToOne      // N : 1 관계 설정 - 여러 이미지는 하나의 책에 속함
    // book_id는 실제로 BookImage 테이블에 외래키 컬럼으로 생성됨. 이를 통해 두 테이블이 연결됨.
    @JoinColumn(name ="book_id", referencedColumnName = "id", nullable = false)
    private Book book;  //Book 객체 참조용(DB 컬럼 아님, 관계 매핑을 위한  필드)
}
