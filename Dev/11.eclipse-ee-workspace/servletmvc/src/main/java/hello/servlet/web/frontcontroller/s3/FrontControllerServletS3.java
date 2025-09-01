package hello.servlet.web.frontcontroller.s3;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import hello.servlet.web.frontcontroller.s3.controller.MemberFormController;
import hello.servlet.web.frontcontroller.s3.controller.MemberListController;
import hello.servlet.web.frontcontroller.s3.controller.MemberSaveController;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="frontControllerServletS3", urlPatterns = "/front-controller/s3/*")
public class FrontControllerServletS3 extends HttpServlet {

	private Map<String, Controller> controllerMap = new HashMap<>();
	
	public FrontControllerServletS3() {
		// 회원가입 폼 요청 => MemberFormController
		controllerMap.put("/front-controller/s3/members/new-form", 
				new MemberFormController());
		// 회원 저장 요청 => MemberSaveController가 처리
		controllerMap.put("/front-controller/s3/members/save", 
				new MemberSaveController());
		// 회원 목록 조회 요청 => MemberListController가 처리
		controllerMap.put("/front-controller/s3/members", 
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
		ModelView mv = controller.process(paramMap);
		
		// 논리적 view 이름을 실제 물리적 경로로 변환
		// 예) "new-form" => mv.getViewName();
		String viewName = mv.getViewName();
		MyView view = viewResolver(viewName);
		
		view.render(mv.getModel(), request, response);
	}

	private MyView viewResolver(String viewName) {
		return new MyView("/WEB-INF/views/" + viewName+ ".jsp" );
	}

	// HttpServletRequest의 모든 파라미터를 Map으로 변환
	private Map<String, String> createParamMap(HttpServletRequest request) {
		// 파라미터를 담은 Map
		Map<String, String> paramMap = new HashMap<String, String>();
		
		request.getParameterNames().asIterator()
			.forEachRemaining(paramName -> 
			paramMap.put(paramName, request.getParameter(paramName)));
		
		return paramMap;
	}
	
	
}
