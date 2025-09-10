package com.example.demo.repository;

import com.example.demo.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
    Repository 인터페이스 선언
        - 인터페이스만 정의하면 Spring Data JPA가 구현체를 자동 생성
        - @Repository 어노테이션 불필요 (JpaRepository 상속으로 자동 인식)ㄴ
 */

public interface HomeRepository extends JpaRepository<Member, Long> {

    // JpaRepository가 기본 제공하는 메소드들
    /*
        저장 관련 : save()
                  saveAll()
                  flush()
        조회 관련 : findById()
                  findAll()
                  findAllById()
                  count()
        삭제 관련 : deleteById()
                  delete()
                  deleteAll()
        페이징 및 정렬 : findAll(Sort sort)
     */
}
