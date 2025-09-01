package hello.servlet.web.frontcontroller.s3.controller;

import java.util.Map;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.s3.Controller;

public class MemberFormController implements Controller {

	@Override
	public ModelView process(Map<String, String> paramMap) {
		return new ModelView("new-form");
	}

}
