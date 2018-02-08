package member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MemberCommandHandler {

	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
}
