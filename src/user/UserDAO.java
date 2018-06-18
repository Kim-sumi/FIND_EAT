package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
	
	private Connection conn; //접근하게 해주는 객체
	private PreparedStatement pstmt;
	private ResultSet rs; //정보담을수 있는 객체
	//외부라이브러리
	public UserDAO() {
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
	//로그인시도하는 함수
	public int login (String userID, String userPassword) {
		String SQL = "SELECT userPassword FROM MEMBER WHERE userID = ?";
		try {
			pstmt = conn.prepareStatement(SQL); //정해진 sql문장을 데이터에 삽입 
			pstmt.setString(1, userID); //? 에 해당하는 내용을 들어가게
			rs = pstmt.executeQuery(); //실행한 결과 담을 수 있는 객체
			
			if (rs.next()) {
				//결과가 존재한다면
				if(rs.getString(1).equals(userPassword)) {
					return 1; //로그인 성공
				}
				else 
					return 0; //비밀번호 불일치 
			}
			return -1; //아이디가 없을때
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -2; //데이터베이스 오류
	}
	
	//회원 가입 함수
	public int join(User user) {
		String SQL = "INSERT INTO MEMBER VALUES (?, ?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getUserID());
			pstmt.setString(2, user.getUserPassword());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getUserGender());
			pstmt.setString(5, user.getUserEmail());
			int rslt = pstmt.executeUpdate();
			pstmt.clearParameters();
			return rslt;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

}
