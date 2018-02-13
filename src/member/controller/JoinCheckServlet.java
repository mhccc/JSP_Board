package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.service.JoinService;

public class JoinCheckServlet extends HttpServlet {
	
	private JoinService joinService = JoinService.getInstance();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		String userid = request.getParameter("userid");
		PrintWriter out = response.getWriter();
		out.print(joinService.joinCheck(userid));
		out.close();
	}

}
