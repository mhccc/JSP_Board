<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include/header.jsp" %>

	<table border="1">
		아이디 : ${member.mem_userid} <br>
		이  름 : ${member.mem_username} <br>
		이메일 : ${member.mem_email} <br>
		가입일 : ${member.mem_regdate} <br>
	</table>

<%@ include file="../include/footer.jsp" %>