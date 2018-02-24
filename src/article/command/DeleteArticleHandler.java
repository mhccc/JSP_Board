package article.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.service.DeleteArticleService;

public class DeleteArticleHandler implements ArticleCommandHandler {

	private DeleteArticleService deleteService = DeleteArticleService.getInstance();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int articleNo = Integer.parseInt(request.getParameter("articleNo"));
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		
		deleteService.delete(articleNo);
		
		return "list.do?pageNo=" + pageNo;
	}

}
