package com.example.jpa.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false, unique = true, length = 45)
    private String title;
    private String author;
    private int price;
    private int page;

    // 책(1) : 리뷰(N)
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Review> reviews;

    // 책(1) : 이미지(N)
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<BookImage> bookImages;

    // 책(1) : 장바구니(N)
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Cart> carts;
}
