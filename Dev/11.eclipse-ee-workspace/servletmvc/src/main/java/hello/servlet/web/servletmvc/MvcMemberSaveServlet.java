package hello.servlet.web.servletmvc;

import java.io.IOException;

import hello.servlet.basic.domain.member.Member;
import hello.servlet.basic.domain.member.MemberRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// (Controller - 회원 저장)
@WebServlet(name = "mvcMemberSaveServlet", urlPatterns = "/servlet-mvc/members/save")
public class MvcMemberSaveServlet extends HttpServlet{

	private MemberRepository memberRepository = MemberRepository.getInstance();
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 클라이언트로부터 전달받은 파라미터를 추출
		String username = request.getParameter("username");
		int age = Integer.parseInt(request.getParameter("age"));
		
		// 2. 비즈니스 로직 처리 (Model)
		// Member 객체 생성
		Member member = new Member(username, age);
		
		// memberRepository를 통해 데이터 저장
		memberRepository.save(member);
		
		// 3. Model에 데이터를 보관
		// -request 객체에 속성으로 저장
		// JSP에서 ${member}
		request.setAttribute("member", member);
		
		System.out.println("test=========================");
		
		// 4. View로 전달
		String viewPath = "/WEB-INF/views/save-result.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
		dispatcher.forward(request, response);
	}
}
