package board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BoardDAO {

	private Connection conn; //접근하게 해주는 객체
	//private PreparedStatement pstmt; 함수가 많아서 혼동될 수 있으니 각 함수에 넣어준다.
	private ResultSet rs; //정보담을수 있는 객체
	//외부라이브러리
	public BoardDAO() {
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
	
	
	public String getDate() { //현재시간 가져오는 함수
		String SQL= "SELECT NOW()";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";//데이터베이스 오류
	}
	
	public int getNext() { //다음게시글 함수
		String SQL= "SELECT nvl(max(m_number),0)+1 FROM market ORDER BY m_number DESC ";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
			return 1; //현재가 첫번째 게시물일 경우
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; //데이터베이스 오류
	}
	
	public int write(String m_name, String userID, String m_kind, String m_address, String m_time, String m_tele, int m_available) {
		String SQL= "INSERT INTO market VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		//String SQL= "INSERT INTO market(m_number, m_name, userID, m_kind, m_address, m_time, m_tele, m_available VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		int aa =0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext());
			pstmt.setString(2, m_name);
			pstmt.setString(3, userID);
			pstmt.setString(4, m_kind);
			pstmt.setString(5, m_address);
			pstmt.setString(6, m_time);
			pstmt.setString(7, m_tele);
			pstmt.setInt(8, m_available);
			aa =pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return -1; //데이터베이스 오류
		}
		return aa;
	}
	
	//글쓰한 게시물을 받아서 나열해주는 함수
		public ArrayList<Board> getList(int number) {
			String SQL= "SELECT * FROM market ORDER BY m_number DESC";
			ArrayList<Board> list = new ArrayList<Board>();
			try {
				PreparedStatement pstmt = conn.prepareStatement(SQL);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					Board board = new Board();
					board.setM_number(rs.getInt(1));
					board.setM_name(rs.getString(2));
					board.setM_kind(rs.getString(4));
					board.setM_address(rs.getString(5));
					board.setM_time(rs.getString(6));
					board.setM_tele(rs.getString(7));
					list.add(board);
				}
				System.out.print(list);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}
	
	public Board getBoard(int m_number) {
		String SQL= "SELECT * FROM MARKET WHERE m_number = ?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1,m_number);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Board board = new Board();
				board.setM_number(rs.getInt(1));
				board.setM_name(rs.getString(2));
				board.setM_kind(rs.getString(4));
				board.setM_address(rs.getString(5));
				board.setM_time(rs.getString(6));
				board.setM_tele(rs.getString(7));
				return board;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Board> serachAddress(String search) {
		ArrayList<Board> list = new ArrayList<Board>();
		System.out.print(search);
		String SQL= "SELECT * FROM MARKET WHERE m_address LIKE '%" + search + "%' ORDER BY m_number DESC";
		System.out.print(SQL);
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Board board = new Board();
				board.setM_number(rs.getInt(1));
				board.setM_name(rs.getString(2));
				board.setM_kind(rs.getString(4));
				board.setM_address(rs.getString(5));
				board.setM_time(rs.getString(6));
				board.setM_tele(rs.getString(7));
				list.add(board);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int delete(int boardId) {
		//String SQL= "UPDATE market set m_available = 0 where m_number = ?";
		String SQL= "DELETE market where m_number = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, boardId);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; //데이터베이스 오류
	}
	
}
