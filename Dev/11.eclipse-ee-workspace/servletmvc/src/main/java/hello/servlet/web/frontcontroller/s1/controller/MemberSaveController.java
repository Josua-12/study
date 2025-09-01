package hello.servlet.web.frontcontroller.s1.controller;

import java.io.IOException;

import hello.servlet.basic.domain.member.Member;
import hello.servlet.basic.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.s1.Controller;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 * 회원 정보를 저장하는 컨트롤러
 * 	- 폼에서 전송된 데이터를 받아서 저장
 * 	- 저장 결과를 JSP로 전달하여 화면 랜더링
 */
public class MemberSaveController implements Controller {

	// 회원 정보 관리하는 Repository (싱글톤 패턴)
	private MemberRepository memberRepository = MemberRepository.getInstance();
	
	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// 1. 요펑 파라미터에서 데이터 추출
		String username = request.getParameter("username");
		int age = Integer.parseInt(request.getParameter("age"));
		
		// 2. 비즈니스 로직 처리
		Member member = new Member(username, age);
		memberRepository.save(member);
		
		// 3. Model에 데이터 저장
		request.setAttribute("member", member);
		
		// 4. View로 포워딩 - 저장 결과를 보여줄 JSP 경로
		String viewPath = "/WEB-INF/views/save-result.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
		dispatcher.forward(request, response);
		
	}

}
