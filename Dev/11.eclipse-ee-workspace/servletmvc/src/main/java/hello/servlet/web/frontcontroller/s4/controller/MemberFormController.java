package hello.servlet.web.frontcontroller.s4.controller;

import java.util.Map;

import hello.servlet.web.frontcontroller.s4.Controller;

public class MemberFormController implements Controller{

	@Override
	public String process(Map<String, String> paramMap, Map<String, Object> model) {
		
		return "new-form";
	}

}
