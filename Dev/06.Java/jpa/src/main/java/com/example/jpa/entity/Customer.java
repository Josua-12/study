package com.example.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //          NOT NULL        UNIQUE      --> username VARCHAR(255) NOT NULL UNIQUE
    @Column(nullable = false, unique = true)
    private String username;
    private String password;
    private String customerName;
    private String occupation;
    private int age;
    private String rating;
    // columnDefinition => DDL 생성시 사용할 SQL 구문 직접 지정
    @Column(columnDefinition = "int default 0")  // ->
    private int reserves;       // 적립금

    // 고객(1) : 리뷰(N)
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Review> reviews;

    // 고객(1) : 장바구니(N)
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Cart> carts;
}
