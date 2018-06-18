package comments;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CommentsDAO {
	
	//데이터 접근 객체
		private Connection conn; //접근하게 해주는 객체
		private PreparedStatement pstmt;
		private ResultSet rs; //정보담을수 있는 객체
		//외부라이브러리
	public CommentsDAO() {
		try {
			String dbURL = "jdbc:oracle:thin:@localhost:1521:orcl";
			String dbID = "hyeon";
			String dbPassword = "dbgusdud412";
			Class.forName("oracle.jdbc.driver.OracleDriver"); //드라이버 찾을수 있도록
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[error]:" + e.getMessage());
		}
	}
	
	//댓글 입력하는 함수
	//public Comments
	
	
	
}
