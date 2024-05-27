package login;

import java.sql.*;

import pkg.DBConnection;

public class LoginDaoImpl implements LoginDao {
	DBConnection DBConn;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	LoginDaoImpl() {
		DBConn = DBConnection.getInstance();
	}

	@Override
	public String login(LoginVO vo) {
		String m = "";
		conn = DBConn.getConnection();
		try {
			String sql = "select * from admin where id = ? and password = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPassword());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				m = rs.getString("id");
			} else {
				m = "";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return m;
	}

}
