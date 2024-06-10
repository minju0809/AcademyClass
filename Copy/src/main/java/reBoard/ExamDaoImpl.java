package reBoard;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import pkg.DBConnection;

public class ExamDaoImpl implements ExamDao {
	
	DBConnection DBConn ;
	
	ExamDaoImpl(){
		 DBConn = DBConnection.getInstance();
	}
	
	private Connection conn ;
	private PreparedStatement pstmt;
	private ResultSet rs ;
	
	@Override
	public List<ExamVo> getSelectAll() {
		
		List<ExamVo> li = new ArrayList<ExamVo>();
				
		conn = DBConn.getConnection();		
		String SQL = "select  *  from  examtbl";
		try {
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ExamVo m = new ExamVo();
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
	public ExamVo getSelectOne(String sno) {
		ExamVo m = new ExamVo();		
		conn = DBConn.getConnection();		
		String SQL = "select  *  from  examtbl where sno = ?";
		try {
			pstmt = conn.prepareStatement(SQL);
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
	public List<ReExamVo> getReSelectAll(String sno) {
		
		List<ReExamVo> li = new ArrayList<ReExamVo>();
				
		conn = DBConn.getConnection();		
		String SQL = "select  *  from  ReExamtbl where sno=? ";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, sno);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ReExamVo m = new ReExamVo();
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
	public void update(ExamVo vo) {
						
		conn = DBConn.getConnection();		
		String SQL = "update Examtbl set sname=?, kor=?, eng=?, math = ?, hist= ?, etc = ? where sno=? ";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, vo.getSname());
			pstmt.setInt(2, vo.getKor());
			pstmt.setInt(3, vo.getEng());
			pstmt.setInt(4, vo.getMath());
			pstmt.setInt(5, vo.getHist());
			pstmt.setString(6, vo.getEtc());
			pstmt.setString(7, vo.getSno());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		
	}

	@Override
	public void ReInsert(ReExamVo vo) {
		conn = DBConn.getConnection();		
		String SQL = "insert  into ReExamtbl (sNo  , name, title, details, today   ) "
				+ " values(?,?,?,?, now()  )  ";
		try {
			pstmt = conn.prepareStatement(SQL);
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
	public void ReDelete(String idx) {
		conn = DBConn.getConnection();		
		String SQL = "delete from ReExamtbl where idx = ? ";				
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, idx);			
			pstmt.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		
	}

	@Override
	public String ckID(String sno) {						
		conn = DBConn.getConnection();		
		String SQL = "select  sno  from  ReExamtbl where sno=? ";
		String str ="" ;
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, sno);
			rs = pstmt.executeQuery();
	        if(rs.next()) {
	        	str ="T";
	        }else {
	        	str ="F";
	        } 
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return str;
	}

	@Override
	public void insert(ExamVo vo) {
		conn = DBConn.getConnection();		
		String SQL = "insert  into Examtbl (sno, sname, kor, eng, math, hist, etc  ) "
				+ " values(?,?,?,?,?,?,?)  ";
		try {
			pstmt = conn.prepareStatement(SQL);
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

}
