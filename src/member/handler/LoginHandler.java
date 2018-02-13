package member.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;

import member.service.InvalidPasswordException;
import member.service.InvalidUseridException;
import member.service.LoginService;
import member.service.User;

public class LoginHandler implements MemberCommandHandler {
	
	private static final String VIEW = "/WEB-INF/views/member/loginForm.jsp";
	private LoginService loginService = LoginService.getInstance();

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

	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String mem_userid = request.getParameter("mem_userid");
		String mem_password = request.getParameter("mem_password");
		
		Map<String, Boolean> errors = new HashMap<>();
		request.setAttribute("errors", errors);
		
		try {
			User user = loginService.login(mem_userid, mem_password);
			request.setAttribute("authUser", user);
			response.sendRedirect(request.getContextPath() + "/index.jsp");
			return null;
		} catch (InvalidUseridException e) {
			errors.put("invalidUserid", true);
			return VIEW;
		} catch (InvalidPasswordException e) {
			errors.put("invalidPassword", true);
			return VIEW;
		}
	}
	
}
