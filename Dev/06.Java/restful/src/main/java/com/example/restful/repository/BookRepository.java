package com.example.restful.repository;

import com.example.restful.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // JpaRepository를 상속받아 기본 CRUD 메서드 자동 제공
    // findAll(), findById(), save(), delete() 등
}
