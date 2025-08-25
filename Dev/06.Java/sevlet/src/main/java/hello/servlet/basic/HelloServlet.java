package hello.servlet.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
/*
    @WebServlet : 서블릿을 등록하는 애노테이션
 */
@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 콘솔 출력 : 서브릿이 호출되었음을 확인
        System.out.println("HelloServlet.service()");
        // request 객체 : 클라이언트 요청 정보를 담고 있음 (톰캣이 제공하는 ServletRequest 구현체)
        System.out.println("request = " + request);
        // response 객체 : 클라이언트에게 응답할 정보를 담은 객체 (톰캣이 제공하는 SevrletResponse 구현체)
        System.out.println("response = " + response);


        // getParameter() : 쿼리 스트링이나 POST body의 파라미터 값을 가져옴
        String username = request.getParameter("username");
        System.out.println("username = " + username);

        // 응답 컨텐츠 타입 설정 : 브라우저에게 어떤 형식의 데이터인지 알려줌
        // - text/plain : 일반 텍스트
        // - text/html : HTML
        // - application/json : JSON
        response.setContentType("text/plain");
        // 응답 인코딩 설정 : 한글 등 유니코드 문자 깨짐 방지
        response.setCharacterEncoding("utf-8");
        // getWriter() : 응답 body에 데이터를 쓸 수 있는 PrintWriter 객체 반환
        // write() : 실제 응답 데이터 전송
        response.getWriter().write("hello " + username);
    }
}