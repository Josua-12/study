package com.example.mvcs3simple.simple.mvc.controller;

import com.example.mvcs3simple.simple.mvc.Controller;
import com.example.mvcs3simple.simple.mvc.ModelView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// 프런트 컨트롤러
public class SimpleFrontController extends HttpServlet {

    private Map<String, Controller> controllers = new HashMap<>();

    public SimpleFrontController() {
        controllers.put("/hello", new CalculatorController());
        controllers.put("/calc", new CalculatorController());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getRequestURI();

        Controller controller = controllers.get(url);
        if (controller == null) {
            response.getWriter().println("404- 페이지를 찾을 수 없습니다.");
            return;
        }

        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);

        String viewPath = viewResolver(mv.getViewName());

        render(mv.getModel(), viewPath, request, response);
    }

    private void render(Map<String, Object> model, String viewPath, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html; charset=UTF-8");

        response.getWriter().println("<html><body>");
        response.getWriter().println("<h1>View : " + viewPath + "</h1>");

        for(Map.Entry<String, Object> entry : model.entrySet()) {
            response.getWriter().println(
                    "<p>" + entry.getKey() + " = " + entry.getValue() + "</p>"
            );
        }
        response.getWriter().println("</body></html>");
    }

    private String viewResolver(String viewName) {
        return "/WEB-INF/views/" + viewName + ".jsp";
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}