package hello.servlet.web.frontcontroller;

import java.util.HashMap;
import java.util.Map;

// Model과 View 정보를 함께 담는 객체
// Spring MVC의 ModelAndView와 동일한 역할
public class ModelView {

	// View 이름 (예: new-form, members)
	// 실제 경로가 아닌 논리적 이름만 저장
	private String viewName;
	
	// Model 데이터를 담는 Map
	// key : String, value : Object
	// HttpServletRequest를 사용하지 않고 순수 Java Map 객체 사용
	private Map<String, Object> model = new HashMap<>();

	public ModelView(String viewName) {
		//super();
		this.viewName = viewName;
	}

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public Map<String, Object> getModel() {
		return model;
	}

	public void setModel(Map<String, Object> model) {
		this.model = model;
	}
	
	
}
