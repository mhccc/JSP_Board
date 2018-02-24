package article.service;

import java.sql.Connection;
import java.sql.SQLException;

import article.dao.ArticleDAO;
import article.dto.ArticleDTO;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;

public class ReadArticleService {

	private static ReadArticleService instance = new ReadArticleService();
	public static ReadArticleService getInstance() {
		return instance;
	}
	private ReadArticleService() {
		
	}
	
	private ArticleDAO articleDAO = ArticleDAO.getInstance();
	
	public ArticleDTO getArticle(int articleNo, boolean increaseReadCount) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			ArticleDTO article = articleDAO.selectByNo(conn, articleNo);
			if (article == null) {
				throw new ArticleNotFoundException();
			}
			
			if (increaseReadCount) {
				articleDAO.increaseReadCount(conn, articleNo);
			}
			
			conn.commit();
			
			return article;
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
}
