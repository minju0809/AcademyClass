package repsd;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import pkg.DBConnection;

public class RepsdDaoImpl implements RepsdDao {
	
	private DBConnection DBConn;
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	RepsdDaoImpl() {
		DBConn = DBConnection.getInstance();
	}

	@Override
	public void insert(RepsdVO vo) {
		conn = DBConn.getConnection();
		String sql = "insert into repsd (sname, title, img, etc, cnt, ref, re_step, re_level) "
				+ " values (?,?,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getSname());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getImg());
			pstmt.setString(4, vo.getEtc());
			pstmt.setInt(5, vo.getCnt());
			pstmt.setInt(6, vo.getRef());
			pstmt.setInt(7, vo.getRe_step());
			pstmt.setInt(8, vo.getRe_level());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(pstmt, conn);
		}
	}

	@Override
	public List<RepsdVO> getSelect(RepsdVO vo) {
		List<RepsdVO> li = new ArrayList<>();
		conn = DBConn.getConnection();
		try {
			String sql;
			if (vo.getCh1() == null || vo.getCh2().equals("")) {
				sql = "select * from repsd order by ref desc, re_step limit ?, ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, vo.getStart());
				pstmt.setInt(2, vo.getPageSize());
			} else {
				sql = "select * from repsd where sname like ? order by ref desc, re_step limit ?, ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + vo.getCh2() + "%");
				pstmt.setInt(2, vo.getStart());
				pstmt.setInt(3, vo.getPageSize());
			}
			rs = pstmt.executeQuery();
			while(rs.next()) {
				RepsdVO m = new RepsdVO();
				m.setIdx(rs.getInt("idx"));
				m.setSname(rs.getString("sname"));
				m.setTitle(rs.getString("title"));
				m.setImg(rs.getString("img"));
				m.setEtc(rs.getString("etc"));
				m.setCnt(rs.getInt("cnt"));
				m.setRef(rs.getInt("ref"));
				m.setRe_step(rs.getInt("re_step"));
				m.setRe_level(rs.getInt("re_level"));
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
	public int ref() {
		int ref = 0;
		conn = DBConn.getConnection();
		String sql = "select nvl(max(ref), 1000) + 1 as ref from repsd";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				ref = rs.getInt("ref");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(rs, pstmt, conn);
		}
		return ref;
	}

	@Override
	public RepsdVO getSelectOne(RepsdVO vo) {
		RepsdVO m = new RepsdVO();
		conn = DBConn.getConnection();
		String sql = "select * from repsd where idx=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getIdx());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				m.setIdx(rs.getInt("idx"));
				m.setSname(rs.getString("sname"));
				m.setTitle(rs.getString("title"));
				m.setImg(rs.getString("img"));
				m.setEtc(rs.getString("etc"));
				m.setCnt(rs.getInt("cnt"));
				m.setRef(rs.getInt("ref"));
				m.setRe_step(rs.getInt("re_step"));
				m.setRe_level(rs.getInt("re_level"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(rs, pstmt, conn);
		}
		return m;
	}

	@Override
	public void reInsert(RepsdVO vo) {
		try {
			conn = DBConn.getConnection();
			String sql = "update repsd set re_step = re_step + 1"
					+ " where ref = ? and re_step > ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getRef());
			pstmt.setInt(2, vo.getRe_step());
			pstmt.executeUpdate();
			
			int ref = vo.getRef();
			int re_step = vo.getRe_step() + 1;
			int re_level = vo.getRe_level() + 1;
			
			sql = "insert into repsd (sname, title, ref, etc, img, cnt, re_step, re_level) "
					+ " values (?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getSname());
			pstmt.setString(2, vo.getTitle());
			pstmt.setInt(3, ref);
			pstmt.setString(4, vo.getEtc());
			pstmt.setString(5, vo.getImg());
			pstmt.setInt(6, vo.getCnt());
			pstmt.setInt(7, re_step);
			pstmt.setInt(8, re_level);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(pstmt, conn);
		}
	}

	@Override
	public int cnt(int idx) {
		int cnt = 0;
		try {
			conn = DBConn.getConnection();
			String sql = "update repsd set cnt = cnt + 1"
					+ " where idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(pstmt, conn);
		}
		return cnt;
	}

	@Override
	public void delete(int idx) {
		try {
			conn = DBConn.getConnection();
			String sql = "delete from repsd where idx = ?";
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
	public void update(RepsdVO vo) {
		try {
			conn = DBConn.getConnection();
			String sql = "update repsd set sname=?, title=?, etc=?"
					+ " where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getSname());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getEtc());
			pstmt.setInt(4, vo.getIdx());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(pstmt, conn);
		}
	}

	@Override
	public void updateFile(RepsdVO vo) {
		try {
			conn = DBConn.getConnection();
			String sql = "update repsd set sname=?, title=?, etc=?, img=?"
					+ " where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getSname());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getEtc());
			pstmt.setString(4, vo.getImg());
			pstmt.setInt(5, vo.getIdx());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(pstmt, conn);
		}
	}

	@Override
	public int totalCount(RepsdVO vo) {
		int tc = 0;
		conn = DBConn.getConnection();
		String sql;
		try {
			if(vo.getCh1() == null || vo.getCh2().equals("")) {
				sql = "select count(*) as tc from repsd";
				pstmt = conn.prepareStatement(sql);
			} else {
				sql = "select count(*) as tc from repsd where sname like ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + vo.getCh2() + "%");
			}
			rs = pstmt.executeQuery();
			if(rs.next()) {
				tc = rs.getInt("tc");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(rs, pstmt, conn);
		}
		return tc;
	}

}
