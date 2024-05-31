package dataset;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import pkg.DBConnection;

public class StockDaoImpl implements StockDao {
	DBConnection DBConn;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public StockDaoImpl() {
		DBConn = DBConnection.getInstance();
	}
	

	@Override
	public void stockDeleteAll() {
		conn = DBConn.getConnection();
		String sql = "delete from stock";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void stockInsert(StockVO vo) {
		conn = DBConn.getConnection();
		String sql = "insert into stock (idx, price, importance, name, share) "
				+ " values (?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getIdx());
			pstmt.setString(2, vo.getPrice());
			pstmt.setString(3, vo.getImportance());
			pstmt.setString(4, vo.getName());
			pstmt.setString(5, vo.getShare());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<StockVO> stockSelectAll() {
		List<StockVO> li = new ArrayList<>();
		conn = DBConn.getConnection();
		String sql = "select * from stock";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				StockVO m = new StockVO();
				m.setIdx(rs.getString("idx"));
				m.setPrice(rs.getString("price"));
				m.setImportance(rs.getString("importance"));
				m.setName(rs.getString("name"));
				m.setShare(rs.getString("share"));
				li.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}


}
