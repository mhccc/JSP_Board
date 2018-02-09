package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.service.JoinService;

public class JoinCheckServlet extends HttpServlet {
	
	private JoinService joinService = JoinService.getInstance();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		response.setContentType("text/html; charset=utf-8"); 
		response.getWriter().write(joinService.joinCheck(userid));
	}

}
