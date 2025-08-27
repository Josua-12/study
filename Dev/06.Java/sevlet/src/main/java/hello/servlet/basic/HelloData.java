package hello.servlet.basic;

import lombok.Getter;
import lombok.Setter;

/*
    DTO (Data Transfer Object) 클래스
        - 계층 간 데이터 전송을 위한 객체
        - 다음과 같은 경우에 사용
            - 클라이언트 <--> 서버 간 데이터 전송 (JSON/XML)
            - Controller <--> Service 계층 간 데이터 전달
            - API 요청/응답 데이터 구조 정의
        - 목적 : 데이터 전송

 */
@Getter
@Setter
public class HelloData {

    private String username;

    private int age;
}
