package hello.servlet.web.frontcontroller.s1.controller;

import java.io.IOException;
import java.rmi.ServerException;

import hello.servlet.web.frontcontroller.s1.Controller;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 * 회원 가입 폼을 보여주는 컨트롤러 
 * 	- 단순히 회원가입 폼 JSP를 보여주는 역할
 */
public class MemberFormController implements Controller{

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// JSP 파일 경로 설정
		// WEB-INF : 외부에서 직접 접근 불가, 서버 내부에서만 접근 가능
		String viewPath = "/WEB-INF/views/new-form.jsp";
		
		// RequestDispatcher : 서블릿에서 JSP로 요청을 전달하는 객체
		// 서블릿에서 처리한 결과를 JSP로 넘겨서 화면을 랜더링
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
		
		// forward : 서버 내부에서 다른 서블릿이나 JSP로 요청을 전달
		// 	- URL이 변경되지 않음 (클라이언트는 모름)
		// 	- request, response 객체가 그대로 전달 (데이터 공유)
		// cf) redirect : 클라이언트에 다른 URL으로 재요청하라고 응답 (URL 변경됨)
		dispatcher.forward(request, response);
	}

}
