package hello.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("---- RequestParamServlet.service() start ----");
        // getParameterNames() : 모든 파라미터의 이름을 Enumeration으로 변환
        // asIterator() : Enumeration을 Iterator로 변환
        // forEachRemaining() : 각 파라미터의 이름에 대해 람다식을 실행하기 위해 사용
        System.out.println("[전체 파라미터 조회] - start");
        request.getParameterNames().asIterator().forEachRemaining(paramName -> {
            System.out.println(paramName + " = " + request.getParameter(paramName));
        });
        System.out.println("[전체 파라미터 조회] - end");
        System.out.println();

        System.out.println("[단일 파라미터 조회] - start");
        String username = request.getParameter("username");
        String age = request.getParameter("age");
        System.out.println("username = " + username);
        System.out.println("age = " + age);
        System.out.println("[단일 파라미터 조회] - end");
        System.out.println();
        // 이름이 같은 복수 파라미터
        String[] usernames = request.getParameterValues("username");
        for (String name : usernames) {
            System.out.println("username = " + name);
        }

        // 단순 "OK" 텍스트 메시지 브라우저 출력
        response.getWriter().write("OK");
    }
}
