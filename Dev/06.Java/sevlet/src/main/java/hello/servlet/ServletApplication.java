package hello.servlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/*
	@ServletComponentScan : 서블릿 관련 컴포넌트를 자동으로 스캔하여 종료
		- @WebServlet 애노테이션이 붙은 클래스를 찾아서 자동 등록
 */
@ServletComponentScan	// 서블릿 자동 등록
/* @SpringBootApplication : Spring Boot의 핵심 어노테이
션
		- @Configuraion : 설정 클래스임을 명시
		- @EnableAutoConfiguration : 자동 설정 활성화
		- @ComponentScan : 컴포넌트 스캔 활성화
 */
@SpringBootApplication	// 스프링 컨테이너 생성
public class ServletApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServletApplication.class, args);
	}
}
