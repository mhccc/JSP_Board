package article.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.service.WriteArticleService;
import article.service.WriteRequest;

public class WriteArticleHandler implements ArticleCommandHandler {

	private static final String VIEW = "/WEB-INF/views/article/writeArticleForm.jsp";
	private WriteArticleService writeService = WriteArticleService.getInstance();
	
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
	
	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		return VIEW;
	}
	
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(request.getParameter("article_writer"));
		Map<String, Boolean> errors = new HashMap<>();
		request.setAttribute("errors", errors);
		
		WriteRequest wReq = new WriteRequest(request.getParameter("article_writer"), request.getParameter("article_title"), request.getParameter("article_content"));
		wReq.validate(errors);
		if (!errors.isEmpty()) {
			return VIEW;
		}
		
		int newArticleNo = writeService.write(wReq);
		request.setAttribute("newArticleNo", newArticleNo);
		
		return "/WEB-INF/views/article/writeSuccess.jsp";
	}
	
}
