package ch0510;

import java.sql.*;

public class JdbcUpdateEx {

	public static void main(String[] args) {
		DBConnection DBConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update users set username=?, userpassword=?, userage=? "
				+ " where userid=?";
		try {
			String id = "ppk2";
			String name = "너구리2";
			String pwd = "1234";
			String bcPwd = BCrypt.hashpw(pwd, BCrypt.gensalt());
			int age = 22;
			
			DBConn = DBConnection.getInstance();
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, bcPwd);
			pstmt.setInt(3, age);
			pstmt.setString(4, id);
			int k = pstmt.executeUpdate();
			if (k == 1) {
				System.out.println("수정 성공");
			} else {
				System.out.println("수정 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(pstmt, conn);
		}
	}

}
