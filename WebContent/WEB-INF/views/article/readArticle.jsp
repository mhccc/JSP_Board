<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include/header.jsp" %>

<!-- Content Wrapper. Contains page content -->
	  <div class="content-wrapper">
	    <!-- Content Header (Page header) -->
	    <section class="content-header">
	      <h1>
	        Board
	        <small>Read article</small>
	      </h1>
	      <ol class="breadcrumb">
	        <li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
	        <li class="active">Here</li>
	      </ol>
	    </section>
	
	    <!-- Main content -->
	    <section class="content container-fluid">

			<div class="row">
		        <div class="col-md-12">
		        
		          <div class="box box-info">
		          	<div class="box-header with-border">
		          	  <h3 class="box-title">${article.article_title}</h3>
		          	  <small class="pull-right"><a href="#">${article.article_writer}</a></small>
		          	</div>
		          	
		            <div class="box-body pad">
		              <div class="text-right"><small><div class="inline" style="margin-right:10px;">HIT : ${article.article_readcount}</div><fmt:formatDate value="${article.article_regdate}" pattern="yyyy-MM-dd HH:mm"/></small></div>
		              
		              <div>
		              	${article.article_content}
		              </div>
		              
		            </div>
		            
			        <div class="box-footer clearfix">
		              footer
		            </div>
		          </div>
		          <!-- /.box -->
		
		        </div>
		        <!-- /.col-->
		      </div>
		      <!-- ./row -->

<%@ include file="../include/footer.jsp" %>