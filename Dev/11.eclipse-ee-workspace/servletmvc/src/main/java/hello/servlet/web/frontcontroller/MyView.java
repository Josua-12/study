package hello.servlet.web.frontcontroller;

import java.io.IOException;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// View 랜더링을 담당하는 객체
// 컨트롤러가 직접 JSP forward를 처리하지 않고 이 객체에 위임
public class MyView {

	// JSP 파일 실제 경로
	private String viewPath;		// /WEB-INF/views/new-form.jsp

	public MyView(String viewPath) {
		// super();
		this.viewPath = viewPath;
	}
	
	// View 랜더링 메서드 
	// 컨트롤러에서 분리된 View 처리 로직을 담당
	public void render(HttpServletRequest request, HttpServletResponse response) 
	 throws ServletException, IOException {
		// RequestDispatcher를 사용한 서버 내부 포워딩
		// 클라이언트는 이 과정을 모름 (URL 변경 없음)
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
		// request에 저장된 Model 데이터와 함께 JSP로 forward
		// JSP에서는 ${member} 같은 EL로 접근 가능
		dispatcher.forward(request, response);
		
	}

	// s3를 위해 추가된 render 메서드 (오버로딩)
	// Model 데이터를 받아서 request attribute로 변환하는 역할 추가 
	public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		modelToRequestAttribute(model, request);
		
		// 기존 render 메서드 호출
		render(request, response);
	}

	// Model의 Map의 데이터를 HttpServletRequest의 attribute로 복사
	// JSP에서 ${key} 형태로 접근 가능하도록 설정
	private void modelToRequestAttribute(Map<String, Object> model, HttpServletRequest request) {
		model.forEach((key, value) -> request.setAttribute(key, value));
	}
	
	
}
