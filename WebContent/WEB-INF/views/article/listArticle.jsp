<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include/header.jsp" %>

	  <!-- Content Wrapper. Contains page content -->
	  <div class="content-wrapper">
	    <!-- Content Header (Page header) -->
	    <section class="content-header">
	      <h1>
	        Board
	        <small>Article list</small>
	      </h1>
	      <ol class="breadcrumb">
	        <li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
	        <li class="active">Here</li>
	      </ol>
	    </section>
	
	    <!-- Main content -->
	    <section class="content container-fluid">

			<!-- Main content -->
			<section class="content">
				<div class="row">
				
					<!-- left column -->
					<div class="col-md-12">
						<div class="box box-info">
						
							<div class="box-body pad">
								<table class="table table-bordered text-center">
									<tr>
										<th style="width: 10px">BNO</th>
										<th style="width: 70%">TITLE</th>
										<th>WRITER</th>
										<th>REGDATE</th>
										<th style="width: 60px">HIT</th>
									</tr>
									
									<c:if test="${articlePage.hasNoArticles()}">
										<td colspan="5">게시글이 없습니다.</td>
									</c:if>
			
									<c:forEach var="article" items="${articlePage.content}">
										<tr>
											<td>${article.article_no}</td>
											<td><a href="read.do?articleNo=${article.article_no}&pageNo=${articlePage.currentPage}">${article.article_title}</a></td>
											<td>${article.article_writer}</td>
											<td><fmt:formatDate value="${article.article_regdate}" pattern="yyyy-MM-dd HH:mm"/></td>
											<td><span class="badge bg-red">${article.article_readcount}</span></td>
										</tr>
									</c:forEach>
									
								</table>
							</div>
							<!-- /.box-body -->
			
							<c:if test="${articlePage.hasArticles()}">
								<div class="box-footer">
									<div class="text-center">
										<ul class="pagination">
				
											<c:if test="${articlePage.startPage > 10}">
												<li><a href="list.do?pageNo=${articlePage.startPage - 10}">&laquo;</a></li>
											</c:if>
				
											<c:forEach var="pNo" begin="${articlePage.startPage}" end="${articlePage.endPage}">
												<li <c:out value="${articlePage.currentPage == pNo? 'class=active' : ''}"/>>
													<a href="list.do?pageNo=${pNo}">${pNo}</a>
												</li>
											</c:forEach>
				
											<c:if test="${articlePage.endPage < articlePage.totalPages}">
												<li><a href="list.do?pageNo=${articlePage.startPage + 10}">&raquo;</a></li>
											</c:if>
				
										</ul>
									</div>
								</div>
								<!-- /.box-footer-->
							</c:if>
							
						</div>
					</div>
					<!--/.col (left) -->
			
				</div>
				<!-- /.row -->
			</section>
			<!-- /.content -->

<%@ include file="../include/footer.jsp" %>