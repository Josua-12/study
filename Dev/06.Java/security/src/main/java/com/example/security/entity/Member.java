package com.example.security.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    // 회원 고유 식별자
    // 회원 계정명 (로그인 ID)
    @Column(unique = true, nullable = false)
    private String username;
    private String password;
    private int age;
    private String email;
}
