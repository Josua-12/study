package com.example.security.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity         // JPA 엔티티임을 명시 (데이터베이스 테이블과 매핑되는 클래스)
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)      // 유니크 제약조건 설정 - 권한명 중복 불가
    private String name;        // 권한명 (ex: Role_USER, Role_MANAGER, Role_ADMIN)
}
