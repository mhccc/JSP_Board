package article.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ArticleCommandHandler {

	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
}
