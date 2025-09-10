package com.initializr.springstarter.config;

import com.initializr.springstarter.entity.Book;
import com.initializr.springstarter.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Stack;

@Component      // Spring 컴포넌트로 등록 - 자동으로 빈 생성
public class InitDataConfig implements CommandLineRunner { // 애플리케이션 실행 시 실행되는
    @Autowired
    private BookService bookService;

    @Override
    public void run(String... args) throws Exception {  // 애플리케이션 시작 후 자동 실행되는
        List<Book> books = bookService.getAll();    // 기존 책 목록 조회

        // 데이터베이스가 비어있는 경우에만 초기 데이터 생성
        if (books.size() == 0) {
            // 첫 번째 책 생성 및 저장
            Book book1 = new Book();
            book1.setSubject("스프링");
            book1.setPrice(30000);
            book1.setAuthor("이순신");
            book1.setPage(600);
            bookService.save(book1);    // 데이터베이스에 저장

            // 두 번째 책 생성 및 저장
            Book book2 = new Book();
            book2.setSubject("파이썬");
            book2.setPrice(32000);
            book2.setAuthor("신사임당");
            book2.setPage(650);
            bookService.save(book2);

            // 세 번째 책 생성 및 저장
            Book book3 = new Book();
            book3.setSubject("LLM");
            book3.setPrice(31000);
            book3.setAuthor("손흥민");
            book3.setPage(620);
            bookService.save(book3);
        }
    }
}
