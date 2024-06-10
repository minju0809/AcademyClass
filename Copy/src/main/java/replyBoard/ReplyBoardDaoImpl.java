package replyBoard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import pkg.DBConnection;
import psdBoard.PsdVo;

public class ReplyBoardDaoImpl implements ReplyBoardDao {

	
	public DBConnection DBConn;
	Connection	conn ;
	PreparedStatement pstmt ; 
	ResultSet rs;
	ReplyBoardDaoImpl(){
		DBConn = DBConnection.getInstance();
	}

	
	@Override
	public void insert(ReplyBoardVo vo) {
		try {
		conn = DBConn.getConnection();
		String SQL  = "insert  into  replyBoard  (sname,title, ref, cnt,re_step,re_level) "
				+ " values(?,?,?,1,1,1)";
		pstmt = conn.prepareStatement(SQL);
		pstmt.setString(1, vo.getSname());
		pstmt.setString(2, vo.getTitle());
		pstmt.setInt(3, vo.getRef());
		pstmt.executeUpdate();
		
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(pstmt, conn);
		}
		
	}

	@Override
	public void reIsert(ReplyBoardVo vo) {
		String SQL ;
		try {
		conn = DBConn.getConnection();
		SQL  = "update replyBoard set re_step = re_step + 1 "
				+ " where ref= ? and re_step > ?  " ;
		pstmt = conn.prepareStatement(SQL);
		pstmt.setInt(1, vo.getRef());
		pstmt.setInt(2, vo.getRe_step());
		pstmt.executeUpdate();
		
		int ref= vo.getRef();
		int re_step= vo.getRe_step() + 1;
		int re_level= vo.getRe_level() + 1;
		
		SQL  = "insert  into  replyBoard  (sname,title, ref, cnt,re_step,re_level) "
				+ " values(?,?,?,?,?,?)";
		pstmt = conn.prepareStatement(SQL);
		pstmt.setString(1, vo.getSname());
		pstmt.setString(2, vo.getTitle());
		pstmt.setInt(3, ref);
		pstmt.setInt(4, 1);
		pstmt.setInt(5, re_step);
		pstmt.setInt(6, re_level);
		pstmt.executeUpdate();
		
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(pstmt, conn);
		}
		
		
	}

	@Override
	public void update(ReplyBoardVo vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(ReplyBoardVo vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ReplyBoardVo getSelectOne(ReplyBoardVo vo) {
		ReplyBoardVo m = new ReplyBoardVo();
		try {
			conn = DBConn.getConnection();
			String SQL  = "select *  from  replyBoard where idx = ? ";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, vo.getIdx());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				m.setIdx(rs.getInt("idx"));
				m.setSname(rs.getString("sname"));
				m.setTitle(rs.getString("title"));
				m.setCnt(rs.getInt("cnt"));
				m.setRef(rs.getInt("ref"));
				m.setRe_step(rs.getInt("re_step"));
				m.setRe_level(rs.getInt("re_level"));
				System.out.println("==> "+ m);
			}
			
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				DBConn.close(rs, pstmt, conn);
			}
		return m;
	}

	@Override
	public List<ReplyBoardVo> getSelectAll(ReplyBoardVo vo) {
		
		List<ReplyBoardVo> li = new ArrayList<ReplyBoardVo>();
		try {
			conn = DBConn.getConnection();
			String SQL  = "select *  from  replyBoard order  by  ref  desc , re_step ";
				
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ReplyBoardVo m = new  ReplyBoardVo();
				m.setIdx(rs.getInt("idx"));
				m.setSname(rs.getString("sname"));
				m.setTitle(rs.getString("title"));
				m.setCnt(rs.getInt("cnt"));
				m.setRef(rs.getInt("ref"));
				m.setRe_step(rs.getInt("re_step"));
				m.setRe_level(rs.getInt("re_level"));	
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
	public int maxRef() {
		int refMax = 0;
		try {
				conn = DBConn.getConnection();
				String SQL  = "select nvl(max(ref),1000) + 1 as ref from replyBoard " ;
				pstmt = conn.prepareStatement(SQL);
				rs = pstmt.executeQuery();
				if(rs.next()) {				
					refMax = rs.getInt("ref");				
				}			
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				DBConn.close(rs, pstmt, conn);
			}
		return refMax;	
		}
}
