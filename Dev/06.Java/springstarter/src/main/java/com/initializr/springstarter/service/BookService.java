package com.initializr.springstarter.service;

import com.initializr.springstarter.entity.Book;
import com.initializr.springstarter.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service    // Service의 서비스 빈으로 등록 (비즈니스 로직 계층)
@AllArgsConstructor // Lombok 모든 필드를 파라미터로 받는 생성자 자동 생성 (의존성 주입)
public class BookService {
    // 생성자 주입 방식 (권장)
    private final BookRepository bookRepository;

    // 모든 책 조회
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    // 책 저장 또는 수정
    public Book save(Book book) {
        // 신규 저장인 경우 (ID가 없는 경우)
        if (book.getId()==null) {
            book.setCreatedAt(LocalDateTime.now());     // 생성 시간 설정
        }
        return bookRepository.save(book);               // 저장 또는 수정 수행
    }

    // 책 삭제
    public void delete(Book book) {
        bookRepository.delete(book);
    }

    // ID로 책 조회 - Oprional로 null 안전성 보장
    public Optional<Book> getById(Long id) {
        return bookRepository.findById(id);
    }
}
