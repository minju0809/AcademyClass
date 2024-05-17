package reBoard;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import pkg.DBConnection;

public class ExamDaoImpl implements ExamDao {
	
	DBConnection DBConn = DBConnection.getInstance();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	@Override
	public List<ExamVO> getSelect() {
		List<ExamVO> li = new ArrayList<>();
		conn = DBConn.getConnection();
		try {
			String sql = "select * from examtbl";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ExamVO m = null;
			while(rs.next()) {
				m = new ExamVO();
				m.setSno(rs.getString("sno"));
				m.setSname(rs.getString("sname"));
				m.setKor(rs.getInt("kor"));
				m.setEng(rs.getInt("eng"));
				m.setMath(rs.getInt("math"));
				m.setHist(rs.getInt("hist"));
				m.setEtc(rs.getString("etc"));
				li.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}

	@Override
	public ExamVO getSelectOne(String sno) {
		ExamVO m = new ExamVO();
		conn = DBConn.getConnection();
		try {
			String sql = "select * from examtbl where sno=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sno);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				m.setSno(rs.getString("sno"));
				m.setSname(rs.getString("sname"));
				m.setKor(rs.getInt("kor"));
				m.setEng(rs.getInt("eng"));
				m.setMath(rs.getInt("math"));
				m.setHist(rs.getInt("hist"));
				m.setEtc(rs.getString("etc"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return m;
	}
	
	@Override
	public String ckID(String sno) {
		conn = DBConn.getConnection();
		String str ="";
		try {
			String sql = "select * from examtbl where sno=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sno);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				str = "T";
			} else {
				str = "F";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return str;
	}


	@Override
	public void insert(ExamVO vo) {
		conn = DBConn.getConnection();
		try {
			String sql = "insert into examtbl (sno, sname, kor, eng, math, hist, etc) "
					+ " values (?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getSno());
			pstmt.setString(2, vo.getSname());
			pstmt.setInt(3, vo.getKor());
			pstmt.setInt(4, vo.getEng());
			pstmt.setInt(5, vo.getMath());
			pstmt.setInt(6, vo.getHist());
			pstmt.setString(7, vo.getEtc());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<ReExamVO> getReExamSelect(String sno) {
		List<ReExamVO> li = new ArrayList<>();
		conn = DBConn.getConnection();
		try {
			String sql = "select * from reexamtbl where sno=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sno);
			rs = pstmt.executeQuery();
			ReExamVO m = null;
			while(rs.next()) {
				m = new ReExamVO();
				m.setIdx(rs.getInt("idx"));
				m.setSno(rs.getString("sno"));
				m.setName(rs.getString("name"));
				m.setTitle(rs.getString("title"));
				m.setDetails(rs.getString("details"));
				m.setToday(rs.getString("today"));
				li.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}

	@Override
	public void reExamInsert(ReExamVO vo) {
		conn = DBConn.getConnection();
		try {
			String sql = "insert into reexamtbl (sno, name, title, details, today) "
					+ " values (?,?,?,?, now())";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getSno());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getTitle());
			pstmt.setString(4, vo.getDetails());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void reExamDelete(int idx) {
		conn = DBConn.getConnection();
		try {
			String sql = "delete from reexamtbl where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
