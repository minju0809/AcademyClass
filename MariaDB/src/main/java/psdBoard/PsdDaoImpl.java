package psdBoard;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import pkg.DBConnection;

public class PsdDaoImpl implements PsdDao {
	
	DBConnection DBConn;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	PsdDaoImpl () {
		DBConn = DBConnection.getInstance();
	}
	
	@Override
	public void insert(PsdVO vo) {
		conn = DBConn.getConnection();
		try {
			String sql = "insert into psd (name, age, age2, photo, etc)"
				+ " values (?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setInt(2, vo.getAge());
			pstmt.setString(3, vo.getAge2());
			pstmt.setString(4, vo.getPhoto());
			pstmt.setString(5, vo.getEtc());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(pstmt, conn);
		}
	}

	@Override
	public List<PsdVO> getSelect(PsdVO vo) {
		List<PsdVO> li = new ArrayList<>();
		try {
			conn = DBConn.getConnection();
			String sql = "select * from psd order by idx desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			PsdVO m = null;
			while(rs.next()) {
				m = new PsdVO();
				m.setIdx(rs.getInt("idx"));
				m.setName(rs.getString("name"));
				m.setAge(rs.getInt("age"));
				m.setAge2(rs.getString("age2"));
				m.setPhoto(rs.getString("photo"));
				m.setEtc(rs.getString("etc"));
				li.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(rs, pstmt, conn);
		}
		return li;
	}

	@Override
	public PsdVO getSelectOne(int idx) {
		PsdVO m = new PsdVO();
		try {
			conn = DBConn.getConnection();
			String sql = "select * from psd where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				m.setIdx(rs.getInt("idx"));
				m.setName(rs.getString("name"));
				m.setAge(rs.getInt("age"));
				m.setAge2(rs.getString("age2"));
				m.setPhoto(rs.getString("photo"));
				m.setEtc(rs.getString("etc"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(rs, pstmt, conn);
		}
		return m;
	}

	@Override
	public void delete(int idx) {
		conn = DBConn.getConnection();
		try {
			String sql = "delete from psd where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(pstmt, conn);
		}
	}

	@Override
	public void update(PsdVO vo) {
		conn = DBConn.getConnection();
		try {
			String sql = "update psd set name=?, age=?, age2=?, etc=?"
					+ " where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setInt(2, vo.getAge());
			pstmt.setString(3, vo.getAge2());
			pstmt.setString(4, vo.getEtc());
			pstmt.setInt(5, vo.getIdx());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(pstmt, conn);
		}
	}

	@Override
	public void updateFile(PsdVO vo) {
		conn = DBConn.getConnection();
		try {
			String sql = "update psd set name=?, age=?, age2=?, photo=?, etc=?"
					+ " where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setInt(2, vo.getAge());
			pstmt.setString(3, vo.getAge2());
			pstmt.setString(4, vo.getPhoto());
			pstmt.setString(5, vo.getEtc());
			pstmt.setInt(6, vo.getIdx());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(pstmt, conn);
		}
	}

}
