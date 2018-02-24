package article.service;

import java.util.Map;

public class ModifyRequest {

	private int article_no;
	private int page_no;
	private String article_writer;
	private String article_title;
	private String article_content;
	
	public ModifyRequest(int article_no, int page_no, String article_writer, String article_title, String article_content) {
		this.article_no = article_no;
		this.page_no = page_no;
		this.article_writer = article_writer;
		this.article_title = article_title;
		this.article_content = article_content;
	}

	public int getArticle_no() {
		return article_no;
	}
	
	public void setArticle_no(int article_no) {
		this.article_no = article_no;
	}

	public int getPage_no() {
		return page_no;
	}

	public void setPage_no(int page_no) {
		this.page_no = page_no;
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

	public String getArticle_content() {
		return article_content;
	}

	public void setArticle_content(String article_content) {
		this.article_content = article_content;
	}

	@Override
	public String toString() {
		return "ModifyRequest [article_no=" + article_no + ", page_no=" + page_no + ", article_writer=" + article_writer
				+ ", article_title=" + article_title + ", article_content=" + article_content + "]";
	}

	public void validate(Map<String, Boolean> errors) {
		if (article_title == null || article_title.trim().isEmpty()) {
			errors.put("title", true);
		}
	}
	
}
