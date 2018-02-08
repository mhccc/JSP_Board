package member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;

import member.model.MemberDTO;
import member.service.JoinService;

public class JoinHandler implements MemberCommandHandler {

	private static final String VIEW = "/WEB-INF/views/member/joinForm.jsp";
	private JoinService joinService = JoinService.getInstance();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request.getMethod().equalsIgnoreCase("GET")) {
			return processForm(request, response);
		} else if (request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request, response);
		} else {
			response.setStatus(Response.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		return VIEW;
	}

	private String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		MemberDTO member = new MemberDTO();
		member.setMem_userid(request.getParameter("userid"));
		member.setMem_password(request.getParameter("password"));
		member.setMem_username(request.getParameter("username"));
		member.setMem_email(request.getParameter("email"));
		
		joinService.join(member);
		return "/WEB-INF/views/member/joinSuccess.jsp";
	}
	
}
