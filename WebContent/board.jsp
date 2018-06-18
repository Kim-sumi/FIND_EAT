<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="board.BoardDAO" %>
<%@ page import="board.Board" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<meta name="viewport" content="width=device-width", initial-scale="1">
<link rel="stylesheet" href="css/bootstrap.css">
<title>FIND EAT</title>
<style type="text/css">
	a, a:hover {
		color: #000000;
		text-decoration: none;
	}
</style>
</head>
<body>
	<%
	String userID = null;
	if(session.getAttribute("userID")!= null){
		userID = (String) session.getAttribute("userID");
	}
	
	%>
	<nav class="navbar navbar-inverse" name="category">
		<div class="navbar=header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="icon-bar"></span> <span class="icon-bar"></span> 
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="main.jsp">FIND EAT</a>

		</div>
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="main.jsp">메인</a>
				<li class="active"><a href="board.jsp">맛집리스트</a>
				<li>
			</ul>
			<%
				/*로그인이 되어 있지 않다면*/
				if(userID == null) {
					
			%>	
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">접속하기<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="login.jsp">로그인</a></li>
						<li><a href="join.jsp">회원가입</a></li>
					</ul>
				</li>
			</ul>
			<% 
				} else {
			%>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">회원관리<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="logoutAction.jsp">로그아웃</a></li>
					</ul>
				</li>
			</ul>
			<%
				}
			%>
		</div>
	</nav>
	
<div class="container">
	<div class="row">
		<table class="table table-striped" style="text-align: center; border: 1px solid #BAB9B5">
			<thead>
				<tr>
					<th style="background-color : #BAB9B5; text-align :center;">번호</th>
					<th style="background-color : #BAB9B5; text-align :center;">음식점 이름</th>
					<th style="background-color : #BAB9B5; text-align :center;">종류</th>
					<th style="background-color : #BAB9B5; text-align :center;">위치</th>
					<th style="background-color : #BAB9B5; text-align :center;">영업시간</th>
					<th style="background-color : #BAB9B5; text-align :center;">전화번호</th>
				</tr>
			</thead>
			<tbody>
			<%
			BoardDAO boardDAO = new BoardDAO();
			ArrayList<Board> list = boardDAO.getList(1);
				for(Board b : list) {
			%>
				<tr>
					<td><%= b.getM_number() %></td>
					<td><a href="view.jsp?m_number=<%= b.getM_number() %>"><%= b.getM_name() %></a></td>
					<td><%= b.getM_kind() %></td>
					<td><%= b.getM_address() %> </td>
					<td><%= b.getM_time() %> </td>
					<td><%= b.getM_tele() %></td>
				</tr>
				<%
					} 
				%>
			</tbody>
		</table>
		<% if(userID != null && userID.equals("admin")) { %>
		<a href="write.jsp" class="btn btn-primary pull-right">글쓰기</a>
		<% } %>
	</div>
</div>

	<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>


</body>
</html>