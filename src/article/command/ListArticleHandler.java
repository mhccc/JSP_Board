package article.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.service.ArticlePage;
import article.service.ListArticleService;

public class ListArticleHandler implements ArticleCommandHandler {

	private static final String VIEW = "/WEB-INF/views/article/listArticle.jsp";
	private ListArticleService listService = ListArticleService.getInstance();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String paramPageNum = request.getParameter("pageNo");
		int pageNum = 1;
		if (paramPageNum != null) {
			pageNum = Integer.parseInt(paramPageNum);
		}
		
		ArticlePage articlePage = listService.getArticlePage(pageNum);
		request.setAttribute("articlePage", articlePage);
		
		return VIEW;
	}

}
