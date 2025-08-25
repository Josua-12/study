package hello.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

// HTTP 요청 정보를 조회하는 다양한 방법을 확인하는 서블릿
@WebServlet(name = "requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
            HTTP 요청 메시지 구조
                1. start Line (요청 라인)
                2. Header (헤더)
                3. Body (본문) - GET 요청에는 보통 없음
         */
        printStartLine(request);        // 요청 라인 정보 출력
        printHeaders(request);          // 모든 헤더 정보 출력
        printHeaderUtils(request);      // 헤더의 편의 조회 기능

        // 브라우저에 간단한 안내 메시지 출력
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.println("<html><body>");
        writer.println("<h2>HTTP 요청 정보 조회</h2>");
        writer.println("<p>콘솔을 확인하세요!</p>");
        writer.println("<hr>");
        writer.println("</body></html>");
    }

    // 자주 사용하는 헤더 정보를 편리하게 조회하는 메소드
    private void printHeaderUtils(HttpServletRequest request) {
        System.out.println("---- Headers 편의 조회 start ----");

        // 서버 이름 (도메인명 또는 IP)
        System.out.println("request.getServerName() : " + request.getServerName());
        // 서버 포트 번호
        System.out.println("request.getServerPort() : " + request.getServerPort());
        System.out.println();

        // Content 관련 헤더 정보 조회
        System.out.println("request.getContentType() : " + request.getContentType());
        // Content-Length 헤더 (전송 데이터의 크기, 바이트 단위)
        System.out.println("request.getContentLength() : " + request.getContentLength());
    }

    // 모든 HTTP 헤더 정보를 출력하는 메소드
    private void printHeaders(HttpServletRequest request) {
        System.out.println("---- Headers - start ----");
        // 스트림 사용
        request.getHeaderNames().asIterator()
                .forEachRemaining(headerName ->
                        System.out.println(headerName + " : " + request.getHeader(headerName)));

        System.out.println("---- Headers - end ----");
        System.out.println();
    }

    // start line 정보를 출력하는 메서드
    // HTTP 요청의 첫 번째 줄에 있는 정보들
    private void printStartLine(HttpServletRequest request) {
        System.out.println("---- REQUEST-LINE - start ----");

        // HTTP 메소드 조회 (GET, POST, PUT, DELETE 등)
        System.out.println("request.getMethod() : " + request.getMethod());  // GET

        // 프로토콜 버전 조회 (HTTP/1.1, HTTP/2 등)
        System.out.println("request.getProtocol() : " + request.getProtocol());  // HTTP/1.1

        // URL의 스키마 조회 (http, https)
        System.out.println("request.getScheme() : " + request.getScheme());  // http

        // 전체 URL 조회 (쿼리 스트링 제외)
        // 예 : http://localhost:8080/request-header
        System.out.println("request.getRequestURL() : " + request.getRequestURL());

        // URI 조회 (도메인을 제외한 경로)
        System.out.println("request.getRequestURI() : " + request.getRequestURI());

        // 쿼리 스트링 조회 (? 뒤의 파라미터들)
        // 예: username=hi&age=20
        System.out.println("request.getQueryString() : " + request.getQueryString());

        // HTTPS 사용 여부 (SSL/TLS 암호화 여부)
        System.out.println("request.isSecure() : " + request.isSecure());

        System.out.println("---- REQUEST-LINE - end ----");
        System.out.println();
    }
}