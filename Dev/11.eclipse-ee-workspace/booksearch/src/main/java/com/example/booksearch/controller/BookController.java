package com.example.booksearch.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.booksearch.model.Book;
import com.example.booksearch.service.BookService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 * 도서 검색 관련 요청을 처리하는 Controller
 * @RequestParam 
 */

@Slf4j		  // 로깅을 위한 Lombok 어노테이션
@Controller   // Spring MVC Controller로 등록
@RequiredArgsConstructor  // final 필드에 대한 생성자 자동 생성
public class BookController {

	private final BookService bookService;
	
	/*
	 * 페이지 라우팅
	 */
	
	/*
	 * 메인 페이지 
	 */
	@GetMapping("/")
	public String home() {
		log.info("메인 페이지 요청");
		return "index";
	}
	
	/*
	 * @RequestParam train 페이지
	 */
	@GetMapping("/request-param-test")
	public String requestParamTest() {
		log.info("@RequestParam train 페이지 요청");
		return "request-param-test";
	}
	
	
	/*
	 * 1. 필수 파라미터 
	 * URL 예시: /books/search?keyword=자바
	 * 
	 * @param keyword 검색 키워드 (필수 파라미터)
	 * @param model 뷰에 데이터 전달용 Model 객체
	 * @return JSP 뷰 이름 
	 */
	@GetMapping("/books/search")
	public String searchBooks(
			@RequestParam("keyword") String keyword, Model model) {
		log.info("도서 검색 요청 - 키워드: {}", keyword);
		
		//서비스를 통해 도서 검색
		List<Book> books = bookService.searchBooks(keyword, null);
		
		//Model에 데이터 추가 (JSP에서 사용)
		model.addAttribute("books", books);
		model.addAttribute("keyword", keyword);
		model.addAttribute("resultCount", books.size());
		model.addAttribute("searchType", "basic");		// 검색 타입 구분
		
		return "request-param-test";		// 같은 페이지로 돌아가서 결과 표시
	}
	
	/*
	 * 2. 선택적 파라미터와 기본값
	 * url 예시 : /books/advanced-search?keyword=스프링&category=IT&page=1
	 			 /books/advanced-search (모든 파라미터 생략 가능)
	 * @param keyword 검색 키워드 (선택적, 기본값: 빈 문자열)
	 * @param category 카테고리 필터 (선택적, 기본값: 빈 문자열)
	 * @param page 페이지 번호 (선택적, 기본값: 1)
	 * @param size 페이즈 크기 (선택적, 기본값: 10)
	 * @param model 뷰에 데이터 전달용
	 * @return JSP 뷰 이름
	 */
	@GetMapping("/books/advanced-search")
	public String advancedSearch(
			@RequestParam(required = false, defaultValue = "", name = "keyword") String keyword, //선택적, 기본값 설정
			@RequestParam(required = false, name = "category") String category, //선택적, null 가능
			@RequestParam(defaultValue = "1", name = "page") int page,   //기본값 1, 자동 타입 변환
			@RequestParam(defaultValue = "10", name = "size") int size,	//기본값 10
			Model model) {
	
		log.info("고급 검색 - 키워드: {}, 카테고리: {}, 페이지: {}, 크기: {}",
				keyword, category, page, size);
		
		//도서 검색
		List<Book> allBooks = bookService.searchBooks(keyword, category);
		
		//페이징 처리
		int totalCount = allBooks.size();
		int startIndex = (page-1) * size;
		int endIndex = Math.min(startIndex + size, totalCount);
		
		List<Book> pagedBooks =
		allBooks.subList(Math.min(startIndex, totalCount), endIndex);
	
		//Model에 여러 데이터 추가
		model.addAttribute("books", pagedBooks);
		model.addAttribute("keyword", keyword);
		model.addAttribute("currentPage", page);
		model.addAttribute("pageSize", size);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("totalPages", (totalCount + size -1) /size);
		model.addAttribute("searchType", "advanced");  //검색타입구분
		
		return "request-param-test";   //같은 페이지로 돌아가서 결과 표시 
	};
}
















