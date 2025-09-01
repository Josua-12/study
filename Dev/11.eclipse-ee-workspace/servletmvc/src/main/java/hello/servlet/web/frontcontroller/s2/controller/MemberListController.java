package hello.servlet.web.frontcontroller.s2.controller;

import java.io.IOException;
import java.util.List;

import hello.servlet.basic.domain.member.Member;
import hello.servlet.basic.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.s2.Controller;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 * 회원 목록을 조회하는 컨트롤러
 * 	- 저장된 모든 회원 정보를 조회
 * 	- 조회 결과를 JSP로 전달하여 테이블 형태로 랜더링
 */
public class MemberListController implements Controller {

	// 회원 정보를 관리하는 Repository (싱글톤)
	private MemberRepository memberRepository = MemberRepository.getInstance();
	
	@Override
	public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 비즈니스 로직 처리
		List<Member> members = memberRepository.findAll();
		
		// 2. Model에 데이터 저장
		request.setAttribute("members", members);
		
		// 3. View로 포워딩
		return new MyView("/WEB-INF/views/members.jsp");
	}

}
