package article.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import article.dto.ArticleDTO;
import jdbc.JdbcUtil;

public class ArticleDAO {

	private static ArticleDAO instance = new ArticleDAO();
	public static ArticleDAO getInstance() {
		return instance;
	}
	
	private ArticleDAO() {
		
	}
	
	public ArticleDTO insert(Connection conn, ArticleDTO article) throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("insert into tbl_article (article_writer, article_title, article_regdate, article_readcount, article_content) values (?, ?, ?, ?, ?)");
			pstmt.setString(1, article.getArticle_writer());
			pstmt.setString(2, article.getArticle_title());
			pstmt.setTimestamp(3, toTimestamp(article.getArticle_regdate()));
			pstmt.setInt(4, article.getArticle_readcount());
			pstmt.setString(5, article.getArticle_content());
			int flag = pstmt.executeUpdate();
			
			if (flag > 0) {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select last_insert_id() from tbl_article");
				if (rs.next()) {
					int newArticleNo = rs.getInt(1);
					return new ArticleDTO(newArticleNo, article.getArticle_writer(), article.getArticle_title(), article.getArticle_regdate(), article.getArticle_readcount(), article.getArticle_content());
				}
			}
			
			return null;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			JdbcUtil.close(pstmt);
		}
	}
	
	public int selectCount(Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) from tbl_article");
			if (rs.next()) {
				return rs.getInt(1);
			}
			
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}
	
	private Timestamp toTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}
	
	public List<ArticleDTO> select(Connection conn, int startRow, int size) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("select * from tbl_article order by article_no desc limit ?, ?");
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, size);
			rs = pstmt.executeQuery();
			List<ArticleDTO> result = new ArrayList<>();
			while (rs.next()) {
				result.add(convertArticle(rs));
			}
			
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	private ArticleDTO convertArticle(ResultSet rs) throws SQLException {
		return new ArticleDTO(rs.getInt("article_no"), rs.getString("article_writer"), rs.getString("article_title"), toDate(rs.getTimestamp("article_regdate")),
				rs.getInt("article_readcount"), rs.getString("article_content"));
	}
	
	private Date toDate(Timestamp timestamp) {
		return new Date(timestamp.getTime());
	}
	
}
