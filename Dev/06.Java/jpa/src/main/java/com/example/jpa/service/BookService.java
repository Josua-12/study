package com.example.jpa.service;

import com.example.jpa.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository bookRepository;    // final이 있는 메서드는 생성자 직접 생성 보다는 어노테이션으로 생성자 주입
}
