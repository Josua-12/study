package hello.servlet.basic.request;

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
@WebServlet : 서블릿을 등록
    - name : 서블릿의 이름 (고유해야함)
    - urlPatterns : 이 서블릿이 처리할 URL 패턴
 */
@WebServlet(name = "requestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // HTTP 요청 메시지의 바디 데이터를 읽기 위한 스트림 객체 획득
        ServletInputStream inputStream = request.getInputStream();
        // 바이트 스트림을 문자열로 변환 - 바이트를 문자로 변환할 때 사용할 인코딩 방식 설정
        String messagebody =
                StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        System.out.println("messagebody = "+ messagebody);

        response.getWriter().write("ok");
    }

}
