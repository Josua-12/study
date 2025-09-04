package com.example.booksearch.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.example.booksearch.model.Book;

import jakarta.annotation.PostConstruct;

/*
 * 도서 데이터를 관리하는 Repository 클래스 
 *  - 실제로는 DB를 사용하지만, 메모리에 데이터 저장 
 */

@Repository			// Spring이 이 클래스를 Repository 빈으로 관리 
public class BookRepository {

	//도서 저장할 리스트 (DB 테이블)
	private List<Book> books = new ArrayList<>();
	
	/*
	 * 초기 데이터 설정
	 * @PostConstruct : 빈(bean) 생성 후 자동으로 실행되는 메서드  
	 * 
	 */
	@PostConstruct
	public void init() {
		//샘플 도서 데이터 추가
		books.add(new Book(1L, "LUVIT 커서로 시작하는 바이브 코딩", "반양장", "IT", 22500, 10));
		books.add(new Book(2L, "아이의 스프링", "윤여진", "교육", 17100, 5));
		books.add(new Book(3L, "바로 배워서 바로 써먹는 바이브 코딩", "박찬", "IT", 19800, 7));
		books.add(new Book(4L, "된다! 미리캔버스&캔바 디자인 수업", "배가을 ", "IT", 21600, 3));
		books.add(new Book(5L, "욕망하는 기획자와 보이지 않는 고릴라", "이규철", "인문", 17820, 8));
		books.add(new Book(6L, "경성풍경", "김상엽", "인문", 90000, 12));
		books.add(new Book(7L, "아는 만큼 스프링", "유홍준", "역사", 19800, 20));
		books.add(new Book(8L, "방구석 삼국지 자바", "기행장군", "역사", 21870, 15));
		books.add(new Book(9L, "자바 만들기", "김지혜", "역사", 25200, 9));
		books.add(new Book(10L, "미술관에 스파이가 있다", "비앙카 보스커", "예술", 20700, 11));
		books.add(new Book(11L, "LUVIT 커서로 시작하는 바이브 코딩", "반양장", "IT", 22500, 10));
		books.add(new Book(12L, "아이의 스프링", "윤여진", "교육", 17100, 5));
		books.add(new Book(13L, "바로 배워서 바로 써먹는 바이브 코딩", "박찬", "IT", 19800, 7));
		books.add(new Book(14L, "된다! 미리캔버스&캔바 디자인 수업", "배가을 ", "IT", 21600, 3));
		books.add(new Book(15L, "욕망하는 기획자와 보이지 않는 고릴라", "이규철", "인문", 17820, 8));
	}
	
	/*
	 * 모든 도서 조회
	 * return : 전체 도서 목록
	 */
	public List<Book> findAll() {
		return new ArrayList<Book>(books);
	}
	
	/*
	 * 키워드로 도서 검색 (제목 또는 저자명에 포함)
	 * @param keyword 검색 키워드
	 * @return 검색된 도서 목록
	 */
	public List<Book> findByKeyword(String keyword) {
		//키워드가 없으면 전체 반환
		if(keyword == null || keyword.trim().isEmpty()) {
			return findAll();
		}
		
		String lowerKeyword = keyword.toLowerCase(); //대소문자 구분없이 검색
		
		//Stream API를 사용한 필터링
		return books.stream()
				.filter(book -> book.getTitle().toLowerCase().contains(lowerKeyword) || // 제목에 키워드 포함
						        book.getAuthor().toLowerCase().contains(lowerKeyword))  // 저자명에 키워드 포함
				.collect(Collectors.toList());
	}
	
	/*
	 * 카테고리별 도서 조회
	 * @param category 카테고리명
	 * @return 해당 카테고리의 도서 목록
	 */
	public List<Book> findByCategory(String category) {
		// 카테고리가 없으면 전체 반환
		if(category == null || category.trim().isEmpty()) {
			return findAll();
		}
		
		// Stream API를 사용한 카테고리 필터링
		return books.stream()
				.filter(book -> book.getCategory().equalsIgnoreCase(category)) // 카테고리 일치
				.collect(Collectors.toList());
	}
	
	/*
	 * 키워드와 카테고리를 모두 고려한 복합 검색 메서드 
	 */
	public List<Book> findByKeywordAndCategory(String keyword, String category) {
		// 두 조건 모두 비어있는지 확인
		if((keyword == null || keyword.trim().isEmpty()) &&
				(category == null || category.trim().isEmpty())) {
			return findAll();	// 검색조건이 하나도 없으면 도서 전체 목록 반환
		}
		
		
		// Stream을 사용한 복합 필터링
		return books.stream()
				.filter(book -> {
					
					boolean keywordMatch = true;
					boolean categoryMatch = true;
					
					// 키워드 확인
					if(keyword != null && !keyword.trim().isEmpty()) {
						String lowerKeyword = keyword.toLowerCase();
						
						keywordMatch = 
								book.getTitle().toLowerCase().contains(lowerKeyword) || // 제목에 키워드 포함
								book.getAuthor().toLowerCase().contains(lowerKeyword);   // 저자명에 키워드 포함
						
					}
					
					// 카테고리 조건 확인
					if(category != null && !category.trim().isEmpty()) {
						categoryMatch = book.getCategory().equalsIgnoreCase(category);
					}	
					
					return keywordMatch && categoryMatch;
					
				})
				.collect(Collectors.toList());
	}
}















