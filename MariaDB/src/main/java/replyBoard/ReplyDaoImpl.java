package replyBoard;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import pkg.DBConnection;

public class ReplyDaoImpl implements ReplyDao {
	
	DBConnection DBConn = DBConnection.getInstance();
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	@Override
	public void insert(ReplyVO vo) {
		conn = DBConn.getConnection();
		String sql = "insert into replyBoard (sname, title, ref, re_step, re_level, cnt) "
				+ " values (?,?,?,1,1,1)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getSname());
			pstmt.setString(2, vo.getTitle());
			pstmt.setInt(3, vo.getRef());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void reInsert(ReplyVO vo) {
		conn = DBConn.getConnection();
		try {
			String sql = "update replyBoard set re_step = re_step+1 "
					+ " where ref = ? and re_step > ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getRef());
			pstmt.setInt(2, vo.getRe_step());
			pstmt.executeUpdate();
			
			int ref = vo.getRef();
			int re_step = vo.getRe_step() + 1;
			int re_level = vo.getRe_level() + 1;
			
			sql = "insert into replyBoard (sname, title, ref, re_step, re_level, cnt) "
					+ " values (?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getSname());
			pstmt.setString(2, vo.getTitle());
			pstmt.setInt(3, ref);
			pstmt.setInt(4, re_step);
			pstmt.setInt(5, re_level);
			pstmt.setInt(6, vo.getCnt());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(ReplyVO vo) {
		conn = DBConn.getConnection();
		String sql = "update replyBoard set sname=?, title=? where idx=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getSname());
			pstmt.setString(2, vo.getTitle());
			pstmt.setInt(3, vo.getIdx());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(ReplyVO vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ReplyVO> getSelect() {
		List<ReplyVO> li = new ArrayList<>();
		conn = DBConn.getConnection();
		String sql = "select * from replyBoard order by ref desc, re_step, idx desc";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ReplyVO m = new ReplyVO();
				m.setIdx(rs.getInt("idx"));
				m.setSname(rs.getString("sname"));
				m.setTitle(rs.getString("title"));
				m.setRef(rs.getInt("ref"));
				m.setRe_step(rs.getInt("re_step"));
				m.setRe_level(rs.getInt("re_level"));
				m.setCnt(rs.getInt("cnt"));
				li.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}

	@Override
	public ReplyVO getSelectOne(int idx) {
		ReplyVO m = new ReplyVO();
		conn = DBConn.getConnection();
		String sql = "select * from replyBoard where idx=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				m.setIdx(rs.getInt("idx"));
				m.setSname(rs.getString("sname"));
				m.setTitle(rs.getString("title"));
				m.setRef(rs.getInt("ref"));
				m.setRe_step(rs.getInt("re_step"));
				m.setRe_level(rs.getInt("re_level"));
				m.setCnt(rs.getInt("cnt"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return m;
	}

	@Override
	public int maxRef() {
		int ref = 0;
		try {
			conn = DBConn.getConnection();
			String sql = "select nvl(max(ref), 1000) + 1 as ref from replyBoard";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				ref = rs.getInt("ref");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ref;
	}

}
