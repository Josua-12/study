package com.example.booksearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/* Spring Boot 메인 애플리케이션 클래스
 * 	- 3개 애노테이션을 포함
 * 		- @Configuration : 설정 클래스임을 명시
 * 		- @EnableAutoConfiguration : 자동설정 활성화
 * 		- @ComponentScan : 컴포넌트 스캔 활성화
 */
@SpringBootApplication
public class BooksearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(BooksearchApplication.class, args);
		System.out.println("\n====================================");
		System.out.println(" 도서 검색 시스템이 시작되었습니다!");
		System.out.println("======================================");
		System.out.println(" 브라우저에서 접속: http://localhost:8080");
		System.out.println(" @RequestParam - URL 파라미터 처리");
		System.out.println("=====================================\n");
	}

}













