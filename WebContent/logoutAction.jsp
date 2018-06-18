<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="user.UserDAO" %>
<%@ page import="java.io.PrintWriter" %>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

<title>JSP게시판 웹사이트</title>
</head>
<body>
<%--로그아웃 페이지--%>
<%
	session.invalidate();
	
%>
<script>
	location.href = 'main.jsp';
</script>

</body>
</html>