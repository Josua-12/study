package hello.servlet.web.frontcontroller.s2.controller;

import java.io.IOException;
import java.rmi.ServerException;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.s2.Controller;
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
	public MyView process(HttpServletRequest request, HttpServletResponse response) {
		return new MyView("/WEB-INF/views/new-form.jsp");
		
	}

}
