package com.library.repository;

import com.library.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*
    * 회원 데이터 접근 레포지터리
        - Member 엔티티의 DB CRUD 연산 기능 제공
        - 기본 CRUD : save(), findById(), findAll(), delete() 등
        - JPA Query Method : findByEmail(), existsByEmail() 등
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    // 이메일로 회원 단건 조회 - 로그인 인증 시 사용자 정보 조회
    // SELECT * FROM members WHERE email = ?
    Optional<Member> findByEmail(String email);

    // 이메일 존재 여부 확인 (중복 제크 전용) - 회원 가입시 이메일 중복 체크
    // SELECT COUNT(*) > 0 FROM members WHERE email = ?
    boolean existsByEmail(String email);
}

