package hello.servlet.web.frontcontroller.s1;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.rmi.ServerException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 * 컨트롤러 인터페이스 
 * 	- 모든 컨트롤러는 이 인터페이스를 구현해야 함 
 * 	- 이를 통해 Front Controller가 일관된 방식으로 각 컨트롤러를 호출 가능
 */
public interface Controller {

	void process(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException;
}
