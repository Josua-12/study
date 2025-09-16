package com.initializr.springstarter.controller;

import com.initializr.springstarter.entity.Book;
import com.initializr.springstarter.entity.Review;
import com.initializr.springstarter.service.BookService;
import com.initializr.springstarter.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller         // Spring MVC 컨트롤러 빈으로 등록 - View를 반환
public class BookController {

    private final BookService bookService;
    private final ReviewService reviewService;

    // 생성자 자동 주입
    // Spring이 자동으로 BookService, ReviewService 인스턴스(빈)를 주입
    public BookController(BookService bookService, ReviewService reviewService) {
        this.bookService = bookService;
        this.reviewService = reviewService;
    }

    @GetMapping("/")    // 루트 경로에 대한 GET 요청 매핑
    public String home(Model model) {       // Model: 뷰에 전달할 데이터를 담는 객체
        List<Book> books = bookService.getAll();   // 모든 책 정보 조회
        model.addAttribute("books", books); // "books"라는 이름으로 뷰에 데이터 전달
        return "home";  // home.html 템플릿 파일 반환
    }

    @GetMapping("/detail/{id}")     // @PathVariable: URL의 {id} 값을 매개변수로 받음.
    public String getDetails(@PathVariable Long id, Model model) {
        //ID로 책 조회
        Optional<Book> bookOptional = bookService.getById(id);

        //책이 존재하는 경우
        if(bookOptional.isPresent()) {
            Book book = bookOptional.get();
            model.addAttribute("book", book);   // 책 정보를 모델에 추가

//            // 평균 평점 계산
//            double averageRating = book.getReviews().stream() // 리뷰 리스트를 스트림으로 변환
//                    .mapToInt(Review::getRating) // 각 리뷰의 rating을 int로 추출
//                    .average()  // 평균 계산
//                    .orElse(0.0);   // 리뷰가 없으면 0.0
//            // 소수점 1자리로 포맷
//            String formattedAverageRating = String.format("%.1f", averageRating);
            // 서비스에서 평균 평점 계산
            Double averageRating =  reviewService.reviewAvgRating(id);

            if(averageRating != null) { // 평균 평점이 존재하는 경우
                String formattedAverageRating = String.format("%.1f", averageRating);
                model.addAttribute("averageRating", formattedAverageRating);
            } else {    // 리뷰가 없어서 평균을 계산할 수 없는 경우
                model.addAttribute("averageRating", "평점이 없습니다.");
            }

            return "detail";

        } else {    //  책이 존재하지 않는 경우 (잘못된 ID)
            return "redirect:/";        // 홈 페이지로 리다이렉트
        }
    }

    // 새 리뷰 등록 처리
    @PostMapping("/register")
    public String reviewRegister(@RequestParam("book_id") Long book_id, Review review) {
        Book book = bookService.getById(book_id)        // book_id로 책 정보 조회
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않는 book id:" + book_id));
        review.setBook(book);       //리뷰에 책 정보 연결 (외래키 설정)
        review.setCreatedAt(LocalDateTime.now());    // 리뷰 작성 시간을 현재 시간으로 설정
        reviewService.save(review); // 리뷰를 데이터베이스에 저장 (INSERT SQL 실행)
        return "redirect:/detail/" + book.getId(); //저장 후 해당 책의 상세 페이지로 리다이렉트
    }

    // 리뷰 삭제 처리
    @PostMapping("/deleteReview") // 폼에서 전송된 review_id, book_id를 파라미터로 받음
    public String deleteReview(Long review_id, Long book_id) {
        reviewService.deleteReviewById(review_id); //해당 ID의 리뷰를 DB에서 삭제(DELETE SQL)
        return "redirect:/detail/" + book_id;   // 삭제후 해당 책의 상세페이지로 리다이렉트
    }

}
