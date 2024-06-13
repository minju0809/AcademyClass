package member;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import majustory.DBConnection;

public class MemberDaoImpl implements MemberDao {

	DBConnection DBConn;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	MemberDaoImpl() {
		DBConn = DBConnection.getInstance();
	}
	
	@Override
	public void insert(MemberVO vo) {
		conn = DBConn.getConnection();
		String sql = "insert into member (mid, mpassword1, mpassword2, mphone,"
				+ " maddr1, maddr2, maddr3, mname, mage, mgender, mgrade)"
				+ " values (?,?,?,?,?,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getMid());
			pstmt.setString(2, vo.getMpassword1());
			pstmt.setString(3, vo.getMpassword2());
			pstmt.setString(4, vo.getMphone());
			pstmt.setString(5, vo.getMaddr1());
			pstmt.setString(6, vo.getMaddr2());
			pstmt.setString(7, vo.getMaddr3());
			pstmt.setString(8, vo.getMname());
			pstmt.setInt(9, vo.getMage());
			pstmt.setString(10, vo.getMgender());
			pstmt.setString(11, vo.getMgrade());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(MemberVO vo) {
		conn = DBConn.getConnection();
		String sql = "update member set mphone=?, maddr1=?, maddr2=?, maddr3=?, "
				+ " mgrade=?, metc=? where mid=? ";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getMphone());
			pstmt.setString(2, vo.getMaddr1());
			pstmt.setString(3, vo.getMaddr2());
			pstmt.setString(4, vo.getMaddr3());
			pstmt.setString(5, vo.getMgrade());
			pstmt.setString(6, vo.getMetc());
			pstmt.setString(7, vo.getMid());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<MemberVO> getSelect() {
		List<MemberVO> li = new ArrayList<>();
		conn = DBConn.getConnection();
		String sql = "select * from member";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberVO m = new MemberVO();
				m.setMid(rs.getString("mid"));
				m.setMpassword1(rs.getString("mpassword1"));
				m.setMpassword2(rs.getString("mpassword2"));
				m.setMphone(rs.getString("mphone"));
				m.setMaddr1(rs.getString("maddr1"));
				m.setMaddr2(rs.getString("maddr2"));
				m.setMaddr3(rs.getString("maddr3"));
				m.setMname(rs.getString("mname"));
				m.setMage(rs.getInt("mage"));
				m.setMgender(rs.getString("mgender"));
				m.setMgrade(rs.getString("mgrade"));
				li.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}


	@Override
	public MemberVO getSelectOne(MemberVO vo) {
		MemberVO m = new MemberVO();
		conn = DBConn.getConnection();
		String sql = "select * from member where mid=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getMid());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				m.setMid(rs.getString("mid"));
				m.setMpassword1(rs.getString("mpassword1"));
				m.setMpassword2(rs.getString("mpassword2"));
				m.setMphone(rs.getString("mphone"));
				m.setMaddr1(rs.getString("maddr1"));
				m.setMaddr2(rs.getString("maddr2"));
				m.setMaddr3(rs.getString("maddr3"));
				m.setMname(rs.getString("mname"));
				m.setMage(rs.getInt("mage"));
				m.setMgender(rs.getString("mgender"));
				m.setMgrade(rs.getString("mgrade"));
				m.setMetc(rs.getString("metc"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return m;
	}

	@Override
	public String login(MemberVO vo) {
		String m = "";
		conn = DBConn.getConnection();
		try {
			String sql = "select * from member where mid = ? and mpassword1 = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getMid());
			pstmt.setString(2, vo.getMpassword1());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				m = rs.getString("mid");
			} else {
				m = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return m;
	}

}
