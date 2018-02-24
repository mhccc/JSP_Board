package article.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.dto.ArticleDTO;
import article.service.ArticleNotFoundException;
import article.service.ModifyArticleService;
import article.service.ModifyRequest;
import article.service.PermissionDeniedException;
import article.service.ReadArticleService;
import member.service.User;

public class ModifyArticleHandler implements ArticleCommandHandler {
	
	private static final String VIEW = "/WEB-INF/views/article/modifyArticleForm.jsp";
	private ReadArticleService readService = ReadArticleService.getInstance();
	private ModifyArticleService modifyService = ModifyArticleService.getInstance();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request.getMethod().equalsIgnoreCase("GET")) {
			return processForm(request, response);
		} else if (request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request, response);
		} else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	private String processForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int articleNo = Integer.parseInt(request.getParameter("articleNo"));
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		User authUser = (User) request.getSession().getAttribute("authUser");
		
		try {
			ArticleDTO article = readService.getArticle(articleNo, false);
			ModifyRequest modReq = new ModifyRequest(articleNo, pageNo, authUser.getMem_userid(), article.getArticle_title(), article.getArticle_content());
			request.setAttribute("modReq", modReq);
			return VIEW;
		} catch (ArticleNotFoundException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}
	
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int articleNo = Integer.parseInt(request.getParameter("articleNo"));
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		User authUser = (User) request.getSession().getAttribute("authUser");
		
		ModifyRequest modReq = new ModifyRequest(articleNo, pageNo, authUser.getMem_userid(), request.getParameter("article_title"), request.getParameter("article_content"));
		
		Map<String, Boolean> errors = new HashMap<>();
		request.setAttribute("errors", errors);
		modReq.validate(errors);
		if (!errors.isEmpty()) {
			return VIEW;
		}
		
		try {
			modifyService.modify(modReq);
			return "read.do?articleNo=" + articleNo + "&pageNo=" + pageNo;
		} catch (ArticleNotFoundException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		} catch (PermissionDeniedException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}

}
