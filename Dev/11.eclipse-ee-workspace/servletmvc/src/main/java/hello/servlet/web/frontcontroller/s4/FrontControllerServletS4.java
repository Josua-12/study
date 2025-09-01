package hello.servlet.web.frontcontroller.s4;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.s4.controller.MemberFormController;
import hello.servlet.web.frontcontroller.s4.controller.MemberListController;
import hello.servlet.web.frontcontroller.s4.controller.MemberSaveController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="frontControllerServletS4", urlPatterns = "/front-controller/s4/*")
public class FrontControllerServletS4 extends HttpServlet{

	private Map<String, Controller> controllerMap = new HashMap<>();
	
	public FrontControllerServletS4() {
		// 회원가입 폼 요청 => MemberFormController
		controllerMap.put("/front-controller/s4/members/new-form", 
				new MemberFormController());
		// 회원 저장 요청 => MemberSaveController가 처리
		controllerMap.put("/front-controller/s4/members/save", 
				new MemberSaveController());
		// 회원 목록 조회 요청 => MemberListController가 처리
		controllerMap.put("/front-controller/s4/members", 
				new MemberListController());
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		
		Controller controller =  controllerMap.get(requestURI);
		
		// 매핑된 컨트롤러가 없으면 404 에러 응답
		if(controller == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);	 // 404
			return;
		}
		
		// HttpServletRequest 대신 Map으로 변환
		Map<String, String> paramMap = createParamMap(request);
		
		// Model을 생성
		Map<String, Object> model = new HashMap<>();
		
		// s4 : Controller는 View 이름(String)만 반환 
		String viewName = controller.process(paramMap, model);
		
		MyView view = viewResolver(viewName);
		view.render(model, request, response);
		
	}

	private MyView viewResolver(String viewName) {
		return new MyView("/WEB-INF/views/" + viewName+ ".jsp" );
	}

	private Map<String, String> createParamMap(HttpServletRequest request) {
		// 파라미터를 담은 Map
		Map<String, String> paramMap = new HashMap<String, String>();
		
		request.getParameterNames().asIterator()
			.forEachRemaining(paramName -> 
			paramMap.put(paramName, request.getParameter(paramName)));
		
		return paramMap;
	}
}
