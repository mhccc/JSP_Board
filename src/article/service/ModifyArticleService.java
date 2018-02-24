package article.service;

import java.sql.Connection;
import java.sql.SQLException;

import article.dao.ArticleDAO;
import article.dto.ArticleDTO;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;

public class ModifyArticleService {
	
	private static ModifyArticleService instance = new ModifyArticleService();
	public static ModifyArticleService getInstance() {
		return instance;
	}
	private ModifyArticleService() {
		
	}
	
	private ArticleDAO articleDAO = ArticleDAO.getInstance();
	
	public void modify(ModifyRequest modReq) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			ArticleDTO article = articleDAO.selectByNo(conn, modReq.getArticle_no());
			if (article == null) {
				throw new ArticleNotFoundException();
			}
			if (!checkModifyAuth(modReq.getArticle_writer(), article)) {
				throw new PermissionDeniedException();
			}
			articleDAO.update(conn, modReq.getArticle_no(), modReq.getArticle_title(), modReq.getArticle_content());
			
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (PermissionDeniedException e) {
			JdbcUtil.rollback(conn);
			e.printStackTrace();
			throw e;
		} finally {
			JdbcUtil.close(conn);
		}
		
	}
	
	private boolean checkModifyAuth(String article_writer, ArticleDTO article) {
		return article_writer.equals(article.getArticle_writer());
	}

}
