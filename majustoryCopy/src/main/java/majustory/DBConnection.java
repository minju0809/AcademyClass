package majustory;

import java.sql.*;

public class DBConnection {

	Connection con = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;

	/*
	 * String url = "jdbc:oracle:thin:@//localhost:1521/xe"; String userId =
	 * "system"; String pass = "1234";
	 */

	String url = "jdbc:mysql://localhost:3306/majustory";
	String userId = "root";
	String pass = "autoset";

	private static DBConnection db = new DBConnection();

	private DBConnection() {
	}

	public static DBConnection getInstance() {
		return db;
	}

	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection(url, userId, pass);
			// System.out.println("con 연결확인:" + con);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}

	public void close(PreparedStatement stmt, Connection con) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void close(ResultSet rs, PreparedStatement stmt, Connection con) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
