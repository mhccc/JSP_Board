package article.dto;

import java.util.Date;

public class ArticleDTO {
	
	private Integer article_no;
	private String article_writer;
	private String article_title;
	private Date article_regdate;
	private Integer article_readcount;
	private String article_content;
	
	public ArticleDTO() {	
		
	}
	
	public ArticleDTO(Integer article_no, String article_writer, String article_title, Date article_regdate, Integer article_readcount, String article_content) {
		this.article_no = article_no;
		this.article_writer = article_writer;
		this.article_title = article_title;
		this.article_regdate = article_regdate;
		this.article_readcount = article_readcount;
		this.article_content = article_content;
	}
	
	public Integer getArticle_no() {
		return article_no;
	}
	public void setArticle_no(Integer article_no) {
		this.article_no = article_no;
	}
	public String getArticle_writer() {
		return article_writer;
	}
	public void setArticle_writer(String article_writer) {
		this.article_writer = article_writer;
	}
	public String getArticle_title() {
		return article_title;
	}
	public void setArticle_title(String article_title) {
		this.article_title = article_title;
	}
	public Date getArticle_regdate() {
		return article_regdate;
	}
	public void setArticle_regdate(Date article_regdate) {
		this.article_regdate = article_regdate;
	}
	public Integer getArticle_readcount() {
		return article_readcount;
	}
	public void setArticle_readcount(Integer article_readcount) {
		this.article_readcount = article_readcount;
	}
	public String getArticle_content() {
		return article_content;
	}
	public void setArticle_content(String article_content) {
		this.article_content = article_content;
	}
	
	@Override
	public String toString() {
		return "ArticleDTO [article_no=" + article_no + ", article_writer=" + article_writer + ", article_title="
				+ article_title + ", article_regdate=" + article_regdate + ", article_readcount=" + article_readcount
				+ ", article_content=" + article_content + "]";
	}
	
}
