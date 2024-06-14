package member;

import java.sql.*;
import java.util.*;
import majustory.DBConnection;

public class MemberDaoImpl implements MemberDao {

	private DBConnection DBConn = DBConnection.getInstance();
	private Connection conn = null;
	private PreparedStatement pstmt =null;
	private ResultSet rs = null;
	
	@Override
	public void insert(MemberVO vo) {
		System.out.println("MemberInsert vo확인:" + vo);
		try {
			conn = DBConn.getConnection();
			String SQL = "INSERT INTO member "
					+ "(mid, mpassword1, mpassword2, mphone, "
					+ " maddr1, maddr2, maddr3, mname, "
					+ " mage, mgender, mgrade, metc) "
					+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?) ";

			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, vo.getMid());
			pstmt.setString(2, vo.getMpassword1());
			pstmt.setString(3, vo.getMpassword2());
			pstmt.setString(4, vo.getMphone());
			pstmt.setString(5, vo.getMaddr1());
			pstmt.setString(6, vo.getMaddr2());
			pstmt.setString(7, vo.getMaddr3());
			pstmt.setString(8, vo.getMname());
			pstmt.setString(9, vo.getMage());
			pstmt.setString(10, vo.getMgender());
			pstmt.setString(11, vo.getMgrade());
			pstmt.setString(12, vo.getMetc());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<HashMap<String, Object>> getSelectAll(MemberVO vo) {
		List<HashMap<String, Object>> li =new ArrayList<HashMap<String, Object>>();
		try {
		conn = DBConn.getConnection();
		String SQL = "select * from member order by mid desc ";
		pstmt  = conn.prepareStatement(SQL);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			HashMap<String, Object>	member = new HashMap<String, Object>();
			member.put("mid",rs.getString("mid"));
			member.put("mpassword1",rs.getString("mpassword1"));
			member.put("mpassword2",rs.getString("mpassword2"));
			member.put("mphone",rs.getString("mphone"));
			member.put("maddr1",rs.getString("maddr1"));
			member.put("maddr2",rs.getString("maddr2"));
			member.put("maddr3",rs.getString("maddr3"));
			member.put("mname",rs.getString("mname"));
			member.put("mage",rs.getString("mage"));
			member.put("mgender",rs.getString("mgender"));
			member.put("mgrade",rs.getString("mgrade"));
			member.put("metc",rs.getString("metc"));
			li.add(member);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return li;
	}

	@Override
	public String loginOK(MemberVO vo) {
		String  login ="";
		try {
		conn = DBConn.getConnection();
		String SQL = "select * from member where mid=? and mpassword1=?  ";
		pstmt  = conn.prepareStatement(SQL);
		pstmt.setString(1, vo.getMid());
		pstmt.setString(2, vo.getMpassword1());
		rs = pstmt.executeQuery();
		
			if(rs.next()) {
				login =rs.getString("mid");
			}else {
				login ="F";	
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return login;
	}

}
