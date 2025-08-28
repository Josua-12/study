package hello.servlet.web.servletmvc;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "mvcMemberFormServlet",
urlPatterns = "/servlet-mvc/member/new-form")
public class MvcMemberFormServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("test=========================");
		request.getRequestDispatcher("/WEB-INF/views/new-form.jsp").forward(request, response);
	}
}
