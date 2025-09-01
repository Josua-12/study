package hello.servlet.web.frontcontroller.s4;

import java.util.Map;

import hello.servlet.web.frontcontroller.ModelView;

public interface Controller {
	
	/*
	 * 1. Model을 파라미터로 받음
	 * 2. View 이름 (String)만 반환 (ModelView 객체 생성 불필요) 
	 * 	- "new-form", "members"
	 */
	String process(Map<String, String> paramMap, Map<String, Object> model);
}
