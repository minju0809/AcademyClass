package summernote;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pkg.DBConnection;

public class SummernoteDaoImpl implements SummernoteDao {

	DBConnection DBConn = null; 
	
	Connection conn = null; 
	PreparedStatement pstmt = null;
	ResultSet rs =null;
	
	public SummernoteDaoImpl(){
		DBConn	= DBConnection.getInstance();	
	}
	
	@Override
	public void insert(SummerVO vo) {
		System.out.println("insert(SummernoteVO vo):" + vo);
		String sql = "insert  into summernote (title, name, etc) "
				+ " values(?,?,?)" ;
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getEtc());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(pstmt, conn);
		}		
	}

	@Override
	public List<Map<String, Object>> select(SummerVO vo) {
		List<Map<String, Object>> li = new ArrayList<Map<String, Object>>();
		try {
			conn = DBConn.getConnection();					
			String SQL = "select *  from summernote " ;
		    pstmt=  conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			HashMap<String, Object> m = null ;
			while(rs.next()) {
			 	m = new HashMap<String, Object>();
			 	m.put("idx",   rs.getInt("idx"));
			 	m.put("title", rs.getString("title"));
			 	m.put("name",  rs.getString("name"));
			 	m.put("etc",   rs.getString("etc"));
			 	m.put("today", rs.getString("today"));
			 	li.add(m);
		 	}			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(rs, pstmt, conn);;
		}		  
		return li;
	}
	
	@Override
	public Map<String, Object> edit(SummerVO vo) {
		Map<String, Object> m = new HashMap<String, Object>();
		try {
			conn = DBConn.getConnection();					
			String SQL = "select *  from summernote where idx=?" ;
		    pstmt=  conn.prepareStatement(SQL);
		    pstmt.setString(1, vo.getIdx());
			rs = pstmt.executeQuery();
			if(rs.next()) {
			 	m.put("idx",   rs.getInt("idx"));
			 	m.put("title", rs.getString("title"));
			 	m.put("name",  rs.getString("name"));
			 	m.put("etc",   rs.getString("etc"));
			 	m.put("today", rs.getTimestamp("today"));
		 	}			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(rs, pstmt, conn);;
		}		  
		return m;
	}

	@Override
	public void update(SummerVO vo) {
		String sql = "update summernote set title=?, name=?, etc=? "
				+ " where idx=?" ;
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getEtc());
			pstmt.setString(4, vo.getIdx());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(pstmt, conn);
		}	
	}

	@Override
	public void delete(SummerVO vo) {
		String sql = "delete from summernote where idx=?" ;
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getIdx());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(pstmt, conn);
		}	
	}

}
