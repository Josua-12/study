package com.initializr.springstarter.repository;

import com.initializr.springstarter.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository     // Spring의 리파지토리 빈으로 등록 (데이터베이스 계층)
public interface BookRepository extends JpaRepository<Book, Long> {
    /*
        JpaRepository<엔티티타입, ID타입>을 상속받아 기본 CRUD 메서드 자동 제공
            1) 기본적으로 JpaRepository에서 제공해주는 메서드를 사용
                - save(), findById(), findAll(), delete, count() 등
            2) 직접 메서드를 만들 수도 있음

     */
}
