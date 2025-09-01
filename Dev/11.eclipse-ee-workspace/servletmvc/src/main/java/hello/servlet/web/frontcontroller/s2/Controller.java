package hello.servlet.web.frontcontroller.s2;

import java.io.IOException;

import hello.servlet.web.frontcontroller.MyView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 * 컨트롤러 인터페이스 
 * 	- 모든 컨트롤러는 이 인터페이스를 구현해야 함 
 * 	- 이를 통해 Front Controller가 일관된 방식으로 각 컨트롤러를 호출 가능
 */
public interface Controller {

	MyView process(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException;
}
