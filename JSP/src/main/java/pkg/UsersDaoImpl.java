package pkg;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDaoImpl implements UsersDao {

	DBConnection dbConn = null;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	UsersDaoImpl () {
		dbConn = DBConnection.getInstance();
	}
	
	@Override
	public void insert(UsersVO vo) {
		String sql = "insert into users (userid, username, userpassword, userage) "
				+ " values (?,?,?,?)";
		try {
			String bcPwd = BCrypt.hashpw(vo.getUserpassword(), BCrypt.gensalt());
			conn = dbConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getUserid());
			pstmt.setString(2, vo.getUsername());
			pstmt.setString(3, bcPwd);
			pstmt.setInt(4, vo.getUserage());
			int k = pstmt.executeUpdate();
			if (k == 1) {
				System.out.println("저장 성공");
			} else {
				System.out.println("저장 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbConn.close(pstmt, conn);
		}
	}

	@Override
	public List<UsersVO> getSelect() {
		List<UsersVO> li = new ArrayList<>();
		try {
			conn = dbConn.getConnection();
			String sql = "select * from users";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			UsersVO m = null;
			while(rs.next()) {
				m = new UsersVO();
				m.setUserid(rs.getString("userid"));
				m.setUsername(rs.getString("username"));
				m.setUserpassword(rs.getString("userpassword"));
				m.setUserage(rs.getInt("userage"));
				li.add(m);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbConn.close(pstmt, conn);
		}
		return li;
	}

}
