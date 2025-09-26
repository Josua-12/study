package com.example.security.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    // 회원 고유 식별자
    // 회원 계정명 (로그인 ID)
    @Column(unique = true, nullable = false, length = 100)
    private String username;
    private String password;
    private String name;
    private int age;
    private String email;

    // 회원 - 권한 다대다 관계 설정 (한 회원은 여러 권한, 한 권한은 여러 회원)
    @ManyToMany(fetch = FetchType.EAGER) // EAGER: 회원 조회시 권한도 함께 즉시 로딩
    @JoinTable(                            // 다대다 관계를 위한 중간 테이블 설정
            name = "member_roles",         // 중간 테이블 이름
            joinColumns = @JoinColumn(name ="member_id"),   // 현재 엔티티(Member)의 FK 컬럼명
            inverseJoinColumns = @JoinColumn(name = "role_id") // 연관 엔티티(Role)의 FK 컬럼명
    )
    private Set<Role> roles;    // 회원이 가진 권한 목록 (Set으로 중복 방지)
}
