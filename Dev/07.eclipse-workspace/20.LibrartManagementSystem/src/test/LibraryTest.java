package test;

import java.time.LocalDate;
import java.util.ArrayList;

import exception.LibraryException;
import model.Book;
import service.Library;

public class LibraryTest {

	public static void main(String[] args) {
		Library library = new Library("중앙 도서관");
		
		// 1. 책 추가 테스트
		try {
			library.addBook("2025-1", "혼공", "신용균", "프로그래밍", LocalDate.of(2025, 1, 1));
			library.addBook("2025-2", "LLM", "이순신", "프로그래밍", LocalDate.of(2025, 5, 1));
			System.out.println("책 2권 추가 완료");
			
			// 2. 중복 ISBN 테스트
			//library.addBook("2025-2", "LLM", "신사임당", "프로그래밍", LocalDate.now());
			
			// 3. 제목 검색 테스트
			ArrayList<Book> results = library.searchBooksByTitle("혼공");
			System.out.println("'혼공' 검색 결과 : " + results.size() + "권");
			
			// 4. 저자 검색 테스트
			results = library.searchBooksByAuthor("이순신");
			System.out.println("'이순신' 검색 결과 : " + results.size() + "권");
			
			// 5. 카테고리 검색 테스트 
			results = library.searchBooksByCategory("프로그래밍");
			System.out.println("'프로그래밍' 검색 결과 : " + results.size() + "권");
		} catch (LibraryException e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}
