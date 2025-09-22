package com.example.jpa.repository;

import com.example.jpa.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // JpaRepository에서 제공해주는 기본 CRUD Method
}
