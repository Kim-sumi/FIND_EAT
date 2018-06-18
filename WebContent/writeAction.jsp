<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="board.BoardDAO"%>
<%@ page import="java.io.PrintWriter"%>
<%request.setCharacterEncoding("UTF-8");%>
<jsp:useBean id="board" class="board.Board" scope="page" />
<jsp:setProperty name="board" property="m_name" />
<jsp:setProperty name="board" property="m_kind" />
<jsp:setProperty name="board" property="m_address" />
<jsp:setProperty name="board" property="m_time" />
<jsp:setProperty name="board" property="m_tele" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

<title>FIND EAT</title>
</head>
<body>

	<%
		String userID = null;
		if (session.getAttribute("userID") != null) {
			userID = (String) session.getAttribute("userID");
		}
		if (userID == null) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('로그인을 하세요.')");
			script.println("location.herf = 'login.jsp'");
			script.println("</script>");
		} else {
			if (board.getM_name() == null ||board.getM_kind() == null || board.getM_address() == null
					|| board.getM_time() == null || board.getM_tele() == null) {
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('입력되지 않은 사항이 있습니다.')");
				script.println("history.back()");
				script.println("</script>");
			} else {
				BoardDAO boardDAO = new BoardDAO();
				int result = boardDAO.write(board.getM_name()
						, board.getUserID()
						, board.getM_kind()
						, board.getM_address()
						, board.getM_time()
						, board.getM_tele()
						, board.getM_availabel()
						);
				
				System.out.println(result);
				if (result == -1) {
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('글쓰기에 실패했습니다.')");
					script.println("history.back()");
					script.println("</script>");
				} else {
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("location.href = 'board.jsp'");
					script.println("</script>");
				}

			}

		}
	%>

</body>
</html>