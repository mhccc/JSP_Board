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
	
	public ArticleDTO getArticle(int article_no, boolean increaseReadCount) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			ArticleDTO article = articleDAO.selectByNo(conn, article_no);
			if (article == null) {
				throw new ArticleNotFoundException();
			}
			
			if (increaseReadCount) {
				articleDAO.increaseReadCount(conn, article_no);
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
