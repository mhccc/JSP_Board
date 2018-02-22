package article.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import article.dao.ArticleDAO;
import article.dto.ArticleDTO;
import jdbc.ConnectionProvider;

public class ListArticleService {

	private static ListArticleService instance = new ListArticleService();
	public static ListArticleService getInstance() {
		return instance;
	}
	
	private ListArticleService() {
		
	}
	
	private ArticleDAO articleDAO = ArticleDAO.getInstance();
	private int size = 15;
	
	public ArticlePage getArticlePage(int pageNum) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			int total = articleDAO.selectCount(conn);
			List<ArticleDTO> content = articleDAO.select(conn, (pageNum-1) * 10, size);
			
			return new ArticlePage(total, pageNum, size, content);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
}
