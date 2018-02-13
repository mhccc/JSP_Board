package member.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.MemberDTO;
import member.service.PrivacyService;

public class PrivacyHandler implements MemberCommandHandler {
	
	private static final String VIEW = "/WEB-INF/views/member/privacyForm.jsp";
	private PrivacyService privacyService = PrivacyService.getInstance();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MemberDTO member = privacyService.getPrivacy("mhccc");
		request.setAttribute("member", member);
		return VIEW;
	}

}
