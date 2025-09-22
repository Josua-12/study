package com.example.jpa.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    @Temporal(TemporalType.TIMESTAMP)
    private Date cartDate=new Date();

    @ManyToOne  // 장바구니(N) : 책(1)
    @JoinColumn(name = "book_id", referencedColumnName = "id", nullable = false)
    private Book book;

    @ManyToOne  // 장바구니(N) : 고객(1)
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    private Customer customer;
}
