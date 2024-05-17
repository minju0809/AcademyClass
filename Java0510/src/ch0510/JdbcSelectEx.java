package ch0510;

import java.sql.*;

public class JdbcSelectEx {

	public static void main(String[] args) {
		DBConnection DBConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from users where userid = ?";
		try {
			String id = "ppk2";
			String pwd = "1234";
			DBConn = DBConnection.getInstance();
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if (BCrypt.checkpw(pwd, rs.getString("userpassword"))) {
					rs.getString("userid");
					rs.getString("username");
					rs.getString("userpassword");
					rs.getInt("userage");
					System.out.println("id: " + rs.getString("userid"));
					System.out.println("username: " + rs.getString("username"));
					System.out.println("userpassword: " + rs.getString("userpassword"));
					System.out.println("userage: " + rs.getString("userage"));
				} else {
					System.out.println("pwd를 확인하세요");
				}
			} else {
				System.out.println("id를 확인하세요");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(pstmt, conn);
		}
	}

}
