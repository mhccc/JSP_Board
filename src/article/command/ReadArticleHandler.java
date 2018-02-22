package article.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.dto.ArticleDTO;
import article.service.ArticleNotFoundException;
import article.service.ReadArticleService;

public class ReadArticleHandler implements ArticleCommandHandler {
	
	private ReadArticleService readService = ReadArticleService.getInstance();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int article_no = Integer.parseInt(request.getParameter("articleNo"));
		
		Map<String, Boolean> errors = new HashMap<>();
		request.setAttribute("errors", errors);
		
		try {
			ArticleDTO article = readService.getArticle(article_no, true);
			request.setAttribute("article", article);
			return "/WEB-INF/views/article/readArticle.jsp";
		} catch (ArticleNotFoundException e) {
			errors.put("articleNotFound", true);
			return null;
		}
	}

}
