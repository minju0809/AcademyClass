package psdBoard;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import pkg.DBConnection;

public class PsdDaoImpl implements PsdDao  {
	
	public DBConnection DBConn;
	Connection	conn ;
	PreparedStatement pstmt ; 
	ResultSet rs;
	PsdDaoImpl(){
		DBConn = DBConnection.getInstance();
	}

	@Override
	public void insert(PsdVo vo) {
		try {
		conn = DBConn.getConnection();
		String SQL  = "insert  into  psd (name, age,age2, photo, etc) "
				+ " values(?,?,?,?)";
		pstmt = conn.prepareStatement(SQL);
		pstmt.setString(1, vo.getName());
		pstmt.setInt(2, vo.getAge());
		pstmt.setString(3, vo.getAge2());
		pstmt.setString(4, vo.getPhoto());
		pstmt.setString(5, vo.getEtc());
		pstmt.executeUpdate();
		
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(pstmt, conn);
		}
	}

	@Override
	public List<PsdVo> select() {
		
		List<PsdVo> li = new ArrayList<>();
		try {
			conn = DBConn.getConnection();
			String SQL  = "select idx, name, age, age2,photo, etc  from psd "
					+ " order  by  idx  desc " ;
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				PsdVo m = new  PsdVo();
				m.setIdx(rs.getInt("idx"));
				m.setName(rs.getString("name"));
				m.setAge(rs.getInt("age"));
				m.setAge2(rs.getString("age2"));
				m.setPhoto(rs.getString("photo"));
				m.setEtc(rs.getString("etc"));
				li.add(m);
			}
			
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				DBConn.close(rs, pstmt, conn);
			}
		return li;
	}

	@Override
	public PsdVo edit(int idx) {
		
		PsdVo  m = new PsdVo();
		try {
			conn = DBConn.getConnection();
			String SQL  = "select idx, name, age, photo, etc  from psd "
					+ " where idx=? " ;
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1,idx);
			rs = pstmt.executeQuery();
			if(rs.next()) {				
				m.setIdx(rs.getInt("idx"));
				m.setName(rs.getString("name"));
				m.setAge(rs.getInt("age"));
				m.setPhoto(rs.getString("photo"));
				m.setEtc(rs.getString("etc"));
				
			}
			
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				DBConn.close(rs, pstmt, conn);
			}
		return m;
	}

	@Override
	public void delete(int idx) {
		try {
		conn = DBConn.getConnection();
		String SQL  = "delete from  psd where idx= ?";
		pstmt = conn.prepareStatement(SQL);
		pstmt.setInt(1, idx);
		pstmt.executeUpdate();		
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(pstmt, conn);
		}		
	}

	@Override
	public void updateFile(PsdVo vo) {
		try {

		conn = DBConn.getConnection();
		String SQL  = "update  psd  set name=?, age=?,age2=?, photo=?, etc=?"
				+ " where idx= ? " ;
		pstmt = conn.prepareStatement(SQL);
		pstmt.setString(1, vo.getName());
		pstmt.setInt(2, vo.getAge());
		pstmt.setString(3, vo.getAge2());
		pstmt.setString(4, vo.getPhoto());
		pstmt.setString(5, vo.getEtc());
		pstmt.setInt(6, vo.getIdx());
		pstmt.executeUpdate();
		
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(pstmt, conn);
		}
		
	}

	@Override
	public void update(PsdVo vo) {
		try {
		conn = DBConn.getConnection();
		String SQL  = "update  psd  set name=?, age=?,age2=?, etc=?"
				+ " where idx= ? " ;
		pstmt = conn.prepareStatement(SQL);
		pstmt.setString(1, vo.getName());
		pstmt.setInt(2, vo.getAge());
		pstmt.setString(3, vo.getAge2());
		pstmt.setString(4, vo.getEtc());
		pstmt.setInt(5, vo.getIdx());
		pstmt.executeUpdate();
		
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(pstmt, conn);
		}
		
	}

}
