package article.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import article.dao.ArticleDAO;
import article.dto.ArticleDTO;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;

public class WriteArticleService {

	private static WriteArticleService instance = new WriteArticleService();
	public static WriteArticleService getInstance() {
		return instance;
	}
	
	private WriteArticleService() {
		
	}
	
	private ArticleDAO articleDAO = ArticleDAO.getInstance();
	
	public Integer write(WriteRequest wReq) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			ArticleDTO article = toArticle(wReq);
			ArticleDTO savedArticle = articleDAO.insert(conn, article);
			if (savedArticle == null) {
				throw new RuntimeException("Fail to insert article");
			}
			
			conn.commit();
			
			return savedArticle.getArticle_no();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (RuntimeException e) {
			JdbcUtil.rollback(conn);
			throw e;
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	private ArticleDTO toArticle(WriteRequest wReq) {
		Date now = new Date();
		return new ArticleDTO(null, wReq.getWriter(), wReq.getTitle(), now, 0, wReq.getContent());
	}
	
}
