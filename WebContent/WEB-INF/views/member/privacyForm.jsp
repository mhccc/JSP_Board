<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include/header.jsp" %>

<!-- Content Wrapper. Contains page content -->
	  <div class="content-wrapper">
	    <!-- Content Header (Page header) -->
	    <section class="content-header">
	      <h1>
	        Profile
	        <small>sub title</small>
	      </h1>
	      <ol class="breadcrumb">
	        <li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
	        <li class="active">Here</li>
	      </ol>
	    </section>
	
	    <!-- Main content -->
	    <section class="content container-fluid">

			<table border="1">
				아이디 : ${member.mem_userid} <br>
				이  름 : ${member.mem_username} <br>
				이메일 : ${member.mem_email} <br>
				가입일 : ${member.mem_regdate} <br>
			</table>
			
			<!-- 회원정보 수정하기 만들어서 privacy.do로 POST 요청하기 -->

<%@ include file="../include/footer.jsp" %>