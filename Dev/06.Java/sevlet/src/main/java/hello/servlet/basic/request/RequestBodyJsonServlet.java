package hello.servlet.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/*
    HTTP 요청 메시지의 바디(body)에 담긴 JSON 데이터를 처리하는 서블릿

       JSON(JavaScript Object Notation)
        - 데이터를 주고받을 때 사용하는 경량 데이터 교환 방식
        - 텍스트 기반이라 사람이 읽기 쉽고, 기계가 파싱하기도 쉬움
        - 현재 REST API의 표준 데이터 형식
        - 예시 : {"username" : "hello", "age" : 20}
 */
@WebServlet(name = "requestBodyJsonServlet", urlPatterns = "/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {

    /*
        ObjectMapper: Jackson 라이브러리의 핵심 클래스

            - 주요 기능
                - JSON -> Java 객체 변환  (역직렬화, Deserialization)
                    -예: {"username" : "hello", "age" : 20} -> HelloData 객체
                - Java 객체 -> JSON 변환 (직렬화, Serialization)
                    -예: HelloData 객체 ->  {"username" : "hello", "age" : 20}
     */

    private ObjectMapper objectMapper = new ObjectMapper();


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //step 1: HTTP 요청 바디에서 JSON 문자열 읽기

        // 요청 바디의 데이터를 읽기 위한 입력 스트림 획득
        ServletInputStream inputStream = request.getInputStream();

        // 바이트 스트림 => 문자열 변환 (UTF-8 인코딩)
        // Postman에서 보낸 JSON {"username": "hello", "age" : 20}
        String messageBody =
                StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        // 읽어온 JSON 문자열을 콘솔에 출력 (디버깅용)
        System.out.println("messageBody = " + messageBody);

        //step 2: JSON 문자열 => Java 객체 변환 (역직렬화)
        /*
            objectMapper.readValue() : JSON을 Java 객체로 변환
                - 매개변수 :
                    1) 변환할 JSON 문자열 : messageBody
                    2) 변환 대상 클래스 타입 : HelloData.class

            동작 과정
                - JSON의 키(key)와 HelloData 클래스의 필드명을 매칭
                - 자동으로 타입 변환
                    - "hello" (문자열) => String username
                    - 20 (숫자) => int age
                - HelloData 객체 생성 후 값 설정
                    - 기본 생성자로 객체 생성
                    - setter 메서드 또는 필드에 직접 값 설정
         */
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);

        //step 3 : 변환된 Java 객체 사용
        System.out.println("helloData.getUsername() =" + helloData.getUsername());
        System.out.println("helloData.getAge() = " + helloData.getAge());

        //step 4: 클라이언트 응답 전송
        response.getWriter().write("ok");
    }
}
