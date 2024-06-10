package ckeditor;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pkg.DBConnection;

public class CkeditorDaoImpl implements CkeditorDao {
	
	DBConnection DBconn;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	CkeditorDaoImpl() {
		DBconn = DBConnection.getInstance();
	}

	@Override
	public void insert(CkeditorVO vo) {
		System.out.println("insert(CkeditorVO vo):" + vo);
		String sql = "insert  into ckeditor (title, name, etc) "
				+ " values(?,?,?)" ;
		try {
			conn = DBconn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getEtc());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBconn.close(pstmt, conn);
		}		
	}

	@Override
	public List<Map<String, Object>> select(CkeditorVO vo) {
		List<Map<String, Object>> li = new ArrayList<Map<String, Object>>();
		try {
			conn = DBconn.getConnection();					
			String SQL = "select *  from ckeditor " ;
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
			DBconn.close(rs, pstmt, conn);;
		}		  
		return li;
	}
	
	@Override
	public Map<String, Object> edit(CkeditorVO vo) {
		Map<String, Object> m = new HashMap<String, Object>();
		try {
			conn = DBconn.getConnection();					
			String SQL = "select *  from ckeditor where idx=?" ;
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
			DBconn.close(rs, pstmt, conn);;
		}		  
		return m;
	}

	@Override
	public void update(CkeditorVO vo) {
		String sql = "update ckeditor set title=?, name=?, etc=? "
				+ " where idx=?" ;
		try {
			conn = DBconn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getEtc());
			pstmt.setString(4, vo.getIdx());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBconn.close(pstmt, conn);
		}	
	}

	@Override
	public void delete(CkeditorVO vo) {
		String sql = "delete from ckeditor where idx=?" ;
		try {
			conn = DBconn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getIdx());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBconn.close(pstmt, conn);
		}	
	}

	@Override
	public void commentInsert(CommentVO vo) {
		System.out.println("insert(CommentVO vo):" + vo);
		String sql = "insert into comment (idx, title, name, content) "
				+ " values(?,?,?,?)" ;
		try {
			conn = DBconn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getIdx());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getContent());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBconn.close(pstmt, conn);
		}	
	}

	@Override
	public void commentDelete(String cidx) {
		String sql = "delete from comment where cidx=?" ;
		try {
			conn = DBconn.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cidx);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBconn.close(pstmt, conn);
		}
	}

	@Override
	public List<Map<String, Object>> commentSelect(CommentVO vo) {
		List<Map<String, Object>> li = new ArrayList<Map<String, Object>>();
		try {
			conn = DBconn.getConnection();					
			String SQL = "select *  from comment where idx=? " ;
		    pstmt=  conn.prepareStatement(SQL);
		    pstmt.setString(1, vo.getIdx());
			rs = pstmt.executeQuery();
			HashMap<String, Object> m = null ;
			while(rs.next()) {
			 	m = new HashMap<String, Object>();
			 	m.put("idx",   rs.getString("idx"));
			 	m.put("cidx",   rs.getString("cidx"));
			 	m.put("title", rs.getString("title"));
			 	m.put("name",  rs.getString("name"));
			 	m.put("content", rs.getString("content"));
			 	m.put("today", rs.getString("today"));
			 	li.add(m);
		 	}			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBconn.close(rs, pstmt, conn);;
		}		  
		return li;
	}

}