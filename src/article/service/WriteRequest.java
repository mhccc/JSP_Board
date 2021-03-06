package article.service;

import java.util.Map;

public class WriteRequest {

	private String article_writer;
	private String article_title;
	private String article_content;
	
	public WriteRequest(String article_writer, String article_title, String article_content) {
		this.article_writer = article_writer;
		this.article_title = article_title;
		this.article_content = article_content;
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
		return "WriteRequest [article_writer=" + article_writer + ", article_title=" + article_title
				+ ", article_content=" + article_content + "]";
	}

	public void validate(Map<String, Boolean> errors) {
		if (article_title == null || article_title.trim().isEmpty()) {
			errors.put("title", true);
		}
	}
	
}
