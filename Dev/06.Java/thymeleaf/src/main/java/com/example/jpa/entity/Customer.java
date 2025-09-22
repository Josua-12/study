package com.example.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
/*
    *회원 엔티티 클래스
    * - JPA 엔티티로 customer 테이블과 매핑
    * - BaseEntity를 상속받아 생성일시/수정일시 자동 관리
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
// 테이블 레벨에서 제약조건 이름을 명시적 지정
@Table(name = "customer",
        uniqueConstraints = {
            @UniqueConstraint(
                    name = "UK_CUSTOMER_USERNAME",  // 명시적 제약조건 이름
                    columnNames = {"username"}
            )
        }
)
public class Customer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //           NOT NULL       UNIQUE    --> username VARCHAR(255) NOT NULL UNIQUE
    @Column(nullable = false, unique = true)
    private String username;
    private String password;
    private String customerName;
    private String occupation;
    private int age;
    private String rating;
    // columnDefinition => DDL 생성시 사용할 SQL 구문 직접 지정
    @Column(columnDefinition = "int default 0")  // -> reserves INT DEFAULT 0
    private int reserves;       // 적립금

    //소프트 삭제 필드 추가
    @Column(columnDefinition = "boolean default false")
    private boolean deleted = false;

    //삭제 시간 기록 (선택사항)
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedAt;

    // 고객(1) : 리뷰 (N)
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Review> reviews;
    // 고객(1) : 장바구니 (N)
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Cart> carts;
}
