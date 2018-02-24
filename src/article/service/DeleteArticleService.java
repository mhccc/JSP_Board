package article.service;

import java.sql.Connection;

import article.dao.ArticleDAO;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;

public class DeleteArticleService {
	
	private static DeleteArticleService instance = new DeleteArticleService();
	public static DeleteArticleService getInstance() {
		return instance;
	}
	private DeleteArticleService() {
		
	}

	private ArticleDAO articleDAO = ArticleDAO.getInstance();
	
	public void delete(int articleNo) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			
			articleDAO.delete(conn, articleNo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
}
