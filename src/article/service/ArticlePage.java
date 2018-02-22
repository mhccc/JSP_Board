package article.service;

import java.util.List;

import article.dto.ArticleDTO;

public class ArticlePage {

	private int total;
	private int currentPage;
	private List<ArticleDTO> content;
	private int totalPages;
	private int startPage;
	private int endPage;
	
	public ArticlePage(int total, int currentPage, int size, List<ArticleDTO> content) {
		this.total = total;
		this.currentPage = currentPage;
		this.content = content;
		if (total == 0) {
			totalPages = 0;
			startPage = 0;
			endPage = 0;
		} else {
			totalPages = total / size;
			if (total % size > 0) {
				totalPages++;
			}
			startPage = currentPage / 10 * 10 + 1;
			int modVal = currentPage % 10;
			if (modVal == 0) {
				startPage -= 10;
			}
			endPage = startPage + 9;
			if (endPage > totalPages) {
				endPage = totalPages;
			}
		}
	}

	public int getTotal() {
		return total;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public List<ArticleDTO> getContent() {
		return content;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}
	
	public boolean hasArticles() {
		return total > 0;
	}
	
	public boolean hasNoArticles() {
		return total == 0;
	}
}
