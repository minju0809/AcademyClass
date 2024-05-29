package shopping;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import pkg.DBConnection;

public class ShoppingDaoImpl implements ShoppingDao {

	DBConnection DBConn;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	ShoppingDaoImpl() {
		DBConn = DBConnection.getInstance();
	}

	public List<MemberVO> getSelect(MemberVO vo) {
		List<MemberVO> li = new ArrayList<>();
		conn = DBConn.getConnection();
		try {
			String sql = "select * from member_tbl_02 order by custno";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MemberVO m = new MemberVO();
				m.setCustno(rs.getInt("custno"));
				m.setCustname(rs.getString("custname"));
				m.setPhone(rs.getString("phone"));
				m.setAddress(rs.getString("address"));
				m.setJoindate(rs.getString("joindate"));
				m.setGrade(rs.getString("grade"));
				m.setCity(rs.getString("city"));
				m.setLatitude(rs.getString("latitude"));
				m.setLongitude(rs.getString("longitude"));
				li.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}

	@Override
	public MemberVO getSelectOne(int custno) {
		MemberVO m = new MemberVO();
		conn = DBConn.getConnection();
		try {
			String sql = "select * from member_tbl_02 where custno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, custno);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				m.setCustno(rs.getInt("custno"));
				m.setCustname(rs.getString("custname"));
				m.setPhone(rs.getString("phone"));
				m.setAddress(rs.getString("address"));
				m.setJoindate(rs.getString("joindate"));
				m.setGrade(rs.getString("grade"));
				m.setCity(rs.getString("city"));
				m.setLatitude(rs.getString("latitude"));
				m.setLongitude(rs.getString("longitude"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return m;
	}

	@Override
	public void insert(MemberVO vo) {
		conn = DBConn.getConnection();
		try {
			String sql = "insert into member_tbl_02 "
					+ " (custno, custname, phone, address, joindate, grade, city) "
					+ " values (?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getCustno());
			pstmt.setString(2, vo.getCustname());
			pstmt.setString(3, vo.getPhone());
			pstmt.setString(4, vo.getAddress());
			pstmt.setString(5, vo.getJoindate());
			pstmt.setString(6, vo.getGrade());
			pstmt.setString(7, vo.getCity());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<MoneyVO> getMoneySelect() {
		List<MoneyVO> li = new ArrayList<>();
		conn = DBConn.getConnection();
		try {
			String sql = "select b.custno, custname, grade, sum(price) as price"
					+ " from member_tbl_02 b join money_tbl_02 m"
					+ " on b.custno = m.custno"
					+ " group by b.custno, custname, grade"
					+ " order by price desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MoneyVO m = new MoneyVO();
				m.setCustno(rs.getInt("custno"));
				m.setCustname(rs.getString("custname"));
				m.setGrade(rs.getString("grade"));
				m.setPrice(rs.getInt("price"));
				li.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}
}
