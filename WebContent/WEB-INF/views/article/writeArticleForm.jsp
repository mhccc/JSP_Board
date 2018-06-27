<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include/header.jsp" %>

	  <!-- Content Wrapper. Contains page content -->
	  <div class="content-wrapper">
	    <!-- Content Header (Page header) -->
	    <section class="content-header">
	      <h1>
	        Board
	        <small>Write article</small>
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
		            <div class="box-body pad">
		              <form action="write.do" method="post">
		              	<input type="hidden" name="article_writer" value="${sessionScope.authUser.mem_userid}">
		              	<div class="form-group">
		              		<label for="article_title">Title</label>
							<input type="text" name="article_title" class="form-control" value="${article_title}">
		              	</div>
						
		              	<textarea id="editor1" name="article_content" rows="20" cols="80">${param.article_content}</textarea>
		              	
		              	<hr>
		              	
			            <div class="text-center">
			            	<input type="submit" class="btn btn-primary" value="Submit">
		          			<button type="button" id="btn_cancel" class="btn btn-warning" onclick="location.href='list.do'">Cancel</button>
			            </div>
		              </form>
		            </div>
		          </div>
		          <!-- /.box -->
		
		        </div>
		        <!-- /.col-->
		      </div>
		      <!-- ./row -->

<%@ include file="../include/footer.jsp" %>

<!-- CK Editor -->
<script src="../resources/bower_components/ckeditor/ckeditor.js"></script>
<script>
  $(function () {
    CKEDITOR.replace('editor1', {
    	height: 420
    });
  })
</script>