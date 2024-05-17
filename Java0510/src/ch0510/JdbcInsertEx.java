package ch0510;

import java.sql.*;

public class JdbcInsertEx {

	public static void main(String[] args) {
		DBConnection DBConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into users (userid, username, userpassword, userage) "
				+ " values (?,?,?,?)";
		try {
			String bcPwd = BCrypt.hashpw("1234", BCrypt.gensalt());
			DBConn = DBConnection.getInstance();
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "ppk2");
			pstmt.setString(2, "돌아온너구리2");
			pstmt.setString(3, bcPwd);
			pstmt.setInt(4, 13);
			int k = pstmt.executeUpdate();
			if (k == 1) {
				System.out.println("저장 성공");
			} else {
				System.out.println("저장 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(pstmt, conn);
		}
	}

}
