package com.example.booksearch.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.booksearch.model.Book;
import com.example.booksearch.repository.BookRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j		//Lombok이 제공하는 로깅 기능 (log 변수 자동 생성)
@Service	//Spring이 이 클래스를 Service 빈으로 관리
@RequiredArgsConstructor	// final 필드에 대한 생성자 자동 생성 (의존성 주입용)
public class BookService {

	// @RequiredArgsConstructor에 의해 생성자 주입됨
	private final BookRepository bookRepository;
	
	/*
	 * 모든 도서 조회
	 * @return 전체 도서 목록 
	 */
	public List<Book> getAllBooks() {
		log.debug("전체 도서 조회 요청");		// 디버그 로그
		List<Book> books = bookRepository.findAll();
		log.info("조회된 도서 수: {}", books.size());  //정보 로그 
		return books;
	}
	
	/*
	 * 키워드와 카테고리로 도서 검색
	 * @param keyword 검색 키워드 (제목 또는 저자명)
	 * @param category 카테고리 필터 
	 * @return 검색된 도서 목록
	 */
	public List<Book> searchBooks(String keyword, String category) {
		// 검색 조건 로깅
		log.debug("도서 검색 - 키워드 {}, 카테고리 {}", keyword, category);
		
		List<Book> results;
		
		// 검색 조건에 따른 분기 처리
		if(keyword != null && !keyword.trim().isEmpty() &&
				category != null && !category.trim().isEmpty()) {
			// 키워드와 카테고리 모두 있는 경우 
			log.info("키워드와 카테고리로 복합 검색");
			results = bookRepository.findByKeywordAndCategory(keyword, category);
		} else if(keyword != null && !keyword.trim().isEmpty()) {
			// 키워드만 있는 경우
			log.info("키워드만 검색: {}", keyword);
			results = bookRepository.findByKeyword(keyword);
		} else if(category != null && !category.trim().isEmpty()) {
			// 카테고리만 있는 경우
			log.info("카테고리만 검색: {}", category);
			results = bookRepository.findByCategory(category);
		} else {
			// 검색 조건이 없는 경우 전체 조회
			log.info("검색 조건 없음 - 전체 도서 반환");
			results = bookRepository.findAll();
		}
		
		log.info("검색 결과: {}건", results.size());
		return results;
	}
	
	/*
	 * 재고가 있는 도서만 필터링
	 * @param books 도서 목록
	 * @return 재고가 있는 도서 목록
	 */
	public List<Book> filterAvailableBooks(List<Book> books) {
		return books.stream()
				.filter(book -> book.getStock() > 0)   // 재고가 0보다 큰것만
				.toList();
	}
}










