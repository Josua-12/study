package hello.servlet.web.frontcontroller.s2;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.s2.controller.MemberFormController;
import hello.servlet.web.frontcontroller.s2.controller.MemberListController;
import hello.servlet.web.frontcontroller.s2.controller.MemberSaveController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 * Front Controller 패턴 구현체
 * 	- 모든 요청을 이 하나의 서블릿이 받아서 처리
 * 	- URL 패턴에 따라 적절한 컨트롤러로 요청을 위임
 */
// urlPatterns = "/front-controller/s1/*" : s1의 하위 모든 URL을 이 서블릿이 처리
@WebServlet(name="frontControllerServletS2", urlPatterns = "/front-controller/s2/*")
public class FrontControllerServletS2 extends HttpServlet {

	// URL과 Controller 매핑 정보를 저장하는 Map
	// Key : URL 경로, Value : 해당 URL을 처리할 컨트롤러 객체
	private Map<String, Controller> controllerMap = new HashMap<>();
	
	// 생성자 : 서블릿이 생성될 때 한 번만 실행됨
	// URL과 컨트롤러 매핑 정보 초기화
	public FrontControllerServletS2() {
		// 회원가입 폼 요청 => MemberFormController
		controllerMap.put("/front-controller/s2/members/new-form", 
				new MemberFormController());
		// 회원 저장 요청 => MemberSaveController가 처리
		controllerMap.put("/front-controller/s2/members/save", 
				new MemberSaveController());
		// 회원 목록 조회 요청 => MemberListController가 처리
		controllerMap.put("/front-controller/s2/members", 
				new MemberListController());
	}
	
	/*
	 * 모든 HTTP 요청 (GET, POST)를 처리하는 컨트롤러 객체
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("FrontControllerServlet.service()");
		
		/*
		 * http://localhost:8080/front-controller/s1/members/new-form.jsp
		 * => /front-controller/s1/members/new-form
		 */
		String requestURI = request.getRequestURI();
		
		// URI에 매핑된 컨트롤러 찾기
		Controller controller =  controllerMap.get(requestURI);
		
		// 매핑된 컨트롤러가 없으면 404 에러 응답
		if(controller == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);	 // 404
			return;
		}
		
		// 컨트롤러 실행
		MyView view = controller.process(request, response);
		// View 랜더링 - 실제 JSP forward
		view.render(request, response);
	}
}
