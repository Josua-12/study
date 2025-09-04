package hello.springmvc2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Springmvc2Application {

	/*	Spring Boot 애플리케이션 실행
	 *  	1) 내장 톰켓 서버 시작 
	 *  	2) Spring 컨테이너(ApplicationContext) 초기화
	 *  	3) 컴포넌트 스캔 및 빈(bean) 등록 
	 *  	4) 자동 설정 적용
	 */
	public static void main(String[] args) {
		SpringApplication.run(Springmvc2Application.class, args);
		System.out.println("=========================================");
		System.out.println("Spring MVC 애플리케이션이 시작되었습니다.");
		System.out.println("http://localhost:8080/members 로 접속하세요.");
		System.out.println("=========================================");
	}

}






