package com.initializr.springstarter.controller;

import com.initializr.springstarter.entity.Book;
import com.initializr.springstarter.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller     //Spring MVC 컨트롤러 빈으로 등록 - View를 반환
public class BookController {


    private final BookService bookService;

    // 생성자 자동 주입
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")        // 루트 경로에 대한 GET 요청 매핑
    public String home(Model model) {       // Model: view에 전달할 데이터를 담는 객체
        List<Book> books = bookService.getAll();   // 모든 책 정보 조회
        model.addAttribute("books", books);     // books라는 이름으로 view에 데이터 전달
        return "home";      // home.html 템플릿 파일 반환
    }

    @GetMapping("/detail/{id}")     // @PathVariable : URL의 {id} 값을 매개변수로 받음
    public String getDetails(@PathVariable Long id, Model model) {
        // ID로 책 조회
        Optional<Book> bookOptional = bookService.getById(id);

        // 책이 존재하는 경우
        if(bookOptional.isPresent()) {
            Book book = bookOptional.get();
            model.addAttribute("book", book);   // 책 정보를 모델에 추가

            return "detail";
        } else {    // 책이 존재하지 않는 경우 (잘못된 ID)
            return "redirect:/";        // 홈 페이지로 리다이렉트
        }
    }
}
