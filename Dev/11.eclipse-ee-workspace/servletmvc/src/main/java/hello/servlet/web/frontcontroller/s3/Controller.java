package hello.servlet.web.frontcontroller.s3;

import java.util.Map;

import hello.servlet.web.frontcontroller.ModelView;

public interface Controller {
	ModelView process(Map<String, String> paramMap);
}
