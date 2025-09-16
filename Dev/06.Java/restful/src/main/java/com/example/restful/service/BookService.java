package com.example.restful.service;

import com.example.restful.entity.Book;
import com.example.restful.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    //생성자 주입 방식
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    //책 정보 저장 또는 수정
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    //전체 책 목록 조회
    public List<Book> findAll() {
        // SELECT * FROM book; 실행
        // 주의: 데이터가 많으면 메모리 이슈 => 페이징 처리 권장
        return bookRepository.findAll();
    }

    //ID로 특정 책 조회
    public Optional<Book> findById(Long id) {
        // SELECT * FROM book WHERE id = ? 실행
        // Optional로 반환 (null 안정성)
        return bookRepository.findById(id);
    }

    //책 삭제
    public void delete(Book book) {
        // DELETE FROM book WHERE id = ? 실행
        bookRepository.delete(book);
    }
}

















