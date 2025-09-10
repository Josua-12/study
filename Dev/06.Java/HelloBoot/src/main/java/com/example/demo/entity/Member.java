package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
/*
 JPA 엔티티 클래스임을 선언
    - 데이터베이스의 테이블과 매핑되는 Java 객체
    - 기본적으로 클래스명과 동일한 테이블명 사용 (MEMBER)
    - @Table(name="다른 이름")으로 테이블명 변경가능
 */
@Data   // getter/setter/toString/equals/hashcode()
public class Member {
    @Id // 이 필드가 테이블의 기본 키(PK)임을 지정
    // @GeneratedValue : 기본키 값의 자동 생성 설정 ( 데이터베이스의 AUTO_INCREMENT 기능 사용)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String name;
    private String role;
}
