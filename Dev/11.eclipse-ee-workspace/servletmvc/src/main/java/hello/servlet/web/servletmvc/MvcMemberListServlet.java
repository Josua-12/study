package hello.servlet.web.servletmvc;

import java.io.IOException;
import java.util.List;

import hello.servlet.basic.domain.member.Member;
import hello.servlet.basic.domain.member.MemberRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// (Controller - 회원 목록 조회)
@WebServlet(name="mvcMemberListServlet", urlPatterns = "/servlet-mvc/members")
public class MvcMemberListServlet extends HttpServlet {

	private MemberRepository memberRepository = MemberRepository.getInstance();
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 비즈니스 로직 처리
		// MemberRepository에서 모든 회원 정보 조회
		List<Member> members = memberRepository.findAll();
		
		// 2. Model에 데이터 저장
		// - request 객체에 회원 목록을 속성으로 저장
		// - JSP ${members}로 접근해서 사용
		request.setAttribute("members", members);
		
		// 3. View로 전달
		String viewPath = "/WEB-INF/views/members.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
		dispatcher.forward(request, response);
	}
}
