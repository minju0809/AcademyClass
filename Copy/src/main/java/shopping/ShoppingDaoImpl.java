package shopping;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import pkg.DBConnection;

public class ShoppingDaoImpl implements ShoppingDao  {

	private DBConnection DBConn = DBConnection.getInstance();
	private Connection conn = null;
	private PreparedStatement pstmt =null;
	private ResultSet rs = null;
	
	@Override
	public void MemberInsert(MemberVO vo) {
		System.out.println("MemberInsert vo확인:" + vo);
		try {
			conn = DBConn.getConnection();
			String SQL = "insert  into  member_tbl_02(custno, custname,phone,address, joindate,grade, city   ) values(?,?,?,?,?,?,?) ";

			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, vo.getCustno());
			pstmt.setString(2, vo.getCustname());
			pstmt.setString(3, vo.getPhone());
			pstmt.setString(4, vo.getAddress());
			pstmt.setString(5, vo.getJoindate());
			pstmt.setString(6, vo.getGrade());
			pstmt.setString(7, vo.getCity());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public  List<HashMap<String, Object>> getMemberSelect(MemberVO vo) {
		List<HashMap<String, Object>> li =new ArrayList<HashMap<String, Object>>();
		try {
		conn = DBConn.getConnection();
		String SQL = "select * from member_tbl_02 order  by custno asc  ";
		pstmt  = conn.prepareStatement(SQL);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			HashMap<String, Object>	member = new HashMap<String, Object>();
			member.put("custno",rs.getInt("custno"));
			member.put("custname",rs.getString("custname"));
			member.put("phone",rs.getString("phone"));
			member.put("address",rs.getString("address"));
			member.put("joindate",rs.getString("joindate"));
			member.put("grade",rs.getString("grade"));
			member.put("city",rs.getString("city"));
			member.put("lat",rs.getString("latitude"));
			member.put("lon",rs.getString("longitude"));
			li.add(member);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return li;
	}
	@Override
	public List<MoneyVO> getMoney() {
		List<MoneyVO> li =new ArrayList<MoneyVO>();
		try {
		conn = DBConn.getConnection();
		String SQL = " 	select  m1.custno custno , custname , grade , sum(price) money "
				+ "	from money_tbl_02 m1 join  member_tbl_02 m2 "
				+ "	on m1.CUSTNO = m2.CUSTNO "
				+ "	group  by m1.custno, custname , grade "
				+ "	order  by sum(price) desc ";
		pstmt  = conn.prepareStatement(SQL);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			MoneyVO	vo = new MoneyVO();
			vo.setCustno(rs.getInt("custno"));
			vo.setCustname(rs.getString("custname"));
			vo.setGrade(rs.getString("grade"));
			vo.setMoney(rs.getInt("money"));			
			li.add(vo);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return li;
	}
	@Override
	public MemberVO getMember(int custno) {
		MemberVO member =new MemberVO();
		try {
		conn = DBConn.getConnection();
		String SQL = "select * from member_tbl_02 where custno = ?  ";
		pstmt  = conn.prepareStatement(SQL);
		pstmt.setInt(1, custno);
		rs = pstmt.executeQuery();
		if(rs.next()) {			
			member.setCustno(rs.getInt("custno"));
			member.setCustname(rs.getString("custname"));
			member.setPhone(rs.getString("phone"));
			member.setAddress(rs.getString("address"));
			member.setJoindate(rs.getString("joindate"));
			member.setGrade(rs.getString("grade"));
			member.setCity(rs.getString("city"));
			member.setLat(rs.getString("latitude"));
			member.setLon(rs.getString("longitude"));
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;
	}

}
