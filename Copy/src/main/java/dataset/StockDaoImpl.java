package dataset;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pkg.DBConnection;

public class StockDaoImpl  implements StockDao  {
	DBConnection DBConn ;
	
	StockDaoImpl(){
		 DBConn = DBConnection.getInstance();
	}
	
	private Connection conn ;
	private PreparedStatement pstmt;
	private ResultSet rs ;
	
	@Override
	public void insert(StockVO vo) {
		conn = DBConn.getConnection();	
		
		String SQL = "insert  into stock   "
				+ "(idx, col1, col2, col3, col4 ) "
				+ " values(?,?,?,?,?)  ";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, vo.getIdx());
			pstmt.setString(2, vo.getCol1());
			pstmt.setString(3, vo.getCol2());
			pstmt.setString(4, vo.getCol3());
			pstmt.setString(5, vo.getCol4());
	
			pstmt.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
	}

	@Override
	public List<StockVO> selectAll(StockVO vo) {
		List<StockVO> li = new ArrayList<StockVO>();
		
		conn = DBConn.getConnection();		
		String SQL = "select  *  from  stock  ";
		try {
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				StockVO m = new StockVO();
				m.setIdx(rs.getString("idx"));
				m.setCol1(rs.getString("col1"));
				m.setCol2(rs.getString("col2"));
				m.setCol3(rs.getString("col3"));
				m.setCol4(rs.getString("col4"));
				li.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return li;
	}

	@Override
	public void delete() {
		conn = DBConn.getConnection();			
		String SQL = "delete from stock ";				
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
		}			
	}

}
