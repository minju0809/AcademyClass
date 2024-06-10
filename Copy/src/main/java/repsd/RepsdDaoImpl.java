package repsd;

import java.sql.*;
import java.util.*;

import pkg.DBConnection;

public class RepsdDaoImpl implements RepsdDao {
	
	private DBConnection DBConn;
	
	private Connection conn ;
	private PreparedStatement pstmt ;
	private ResultSet rs = null;
	
	String SQL ;
	public RepsdDaoImpl(){
		DBConn	= DBConnection.getInstance();
	}
	
	@Override
	public void rePsdInsert(RepsdVo vo) {
	  conn 	= DBConn.getConnection();
	  SQL = "insert  into repsd "
	  		+ " (sname, title, img, etc, cnt, ref, re_step ,re_level   ) "
	  		+ " values (?,?,?,?,?,?,?,?) ";
		 try {
			pstmt=  conn.prepareStatement(SQL);
			pstmt.setString(1, vo.getSname());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getImg());
			pstmt.setString(4, vo.getEtc());
			pstmt.setInt(5,  vo.getCnt());
			pstmt.setInt(6,  vo.getRef());
			pstmt.setInt(7,  vo.getRe_step());
			pstmt.setInt(8,  vo.getRe_level());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(pstmt, conn);
		}		  
	}


	@Override
	public int ref() {
		int ref = 0; 
		 try {
			conn = DBConn.getConnection();
			SQL = "select nvl(max(ref),1000) + 1 as ref  from repsd " ;  		
			pstmt=  conn.prepareStatement(SQL);				
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				 ref = rs.getInt("ref");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(rs, pstmt, conn);;
		}		  
	return ref;
	}

	@Override
	public HashMap<String, Object> getRePsdSelectOne(RepsdVo vo) {
		HashMap<String, Object> m = new HashMap<String, Object>(); 
		 try {
			conn = DBConn.getConnection();
			SQL = "select *  from repsd where idx = ? " ;  		
			pstmt=  conn.prepareStatement(SQL);
			pstmt.setInt(1, vo.getIdx());
			rs = pstmt.executeQuery();			
			if(rs.next()) {
			 	m.put("idx", rs.getInt("idx"));
			 	m.put("sname", rs.getString("sname"));
			 	m.put("title", rs.getString("title"));
			 	m.put("img", rs.getString("img"));
				m.put("etc", rs.getString("etc"));
			 	m.put("cnt", rs.getInt("cnt"));
			 	m.put("ref", rs.getInt("ref"));
			 	m.put("re_step", rs.getInt("re_step"));
			 	m.put("re_level", rs.getInt("re_level"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(rs, pstmt, conn);;
		}		  
	return m;
	}

	@Override
	public void reIsert(RepsdVo vo) {
		System.out.println("reIsert:"+vo);
		String SQL ;
		try {
		conn = DBConn.getConnection();
		SQL  = "update repsd set re_step = re_step + 1 "
				+ " where ref= ? and re_step > ?  " ;
		pstmt = conn.prepareStatement(SQL);
		pstmt.setInt(1, vo.getRef());
		pstmt.setInt(2, vo.getRe_step());
		pstmt.executeUpdate();
		
		int ref= vo.getRef();
		int re_step= vo.getRe_step() + 1;
		int re_level= vo.getRe_level() + 1;
		
		SQL  = "insert  into  repsd  (sname,title, ref, cnt,re_step,re_level, img, etc) "
				+ " values(?,?,?,?,?,?,?,?)";
		pstmt = conn.prepareStatement(SQL);
		pstmt.setString(1, vo.getSname());
		pstmt.setString(2, vo.getTitle());
		pstmt.setInt(3, ref);
		pstmt.setInt(4, 0);
		pstmt.setInt(5, re_step);
		pstmt.setInt(6, re_level);
		pstmt.setString(7, vo.getImg());
		pstmt.setString(8, vo.getEtc());
		pstmt.executeUpdate();
		
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(pstmt, conn);
		}
		
		
	}

	@Override
	public void cnt(int idx) {
		
		String SQL ;
		try {
		conn = DBConn.getConnection();
		SQL  = "update repsd set cnt = cnt + 1 "
				+ " where idx= ?  " ;
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
	public void reDelete(RepsdVo vo) {
		  conn 	= DBConn.getConnection();
		  SQL = "delete from repsd where idx = ? ";
			 try {
				pstmt=  conn.prepareStatement(SQL);
				pstmt.setInt(1, vo.getIdx());
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBConn.close(pstmt, conn);
			}		  
		
	}

	@Override
	public void reUpdateFileIn(RepsdVo vo) {
		  conn 	= DBConn.getConnection();
		  SQL = "update  repsd set "
		  		+ " sname =?, title=?, img=?, etc=? "
		  		+ " where idx=? ";
			 try {
				pstmt=  conn.prepareStatement(SQL);
				pstmt.setString(1, vo.getSname());
				pstmt.setString(2, vo.getTitle());
				pstmt.setString(3, vo.getImg());
				pstmt.setString(4, vo.getEtc());
				pstmt.setInt(5,  vo.getIdx());
				
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBConn.close(pstmt, conn);
			}		  
		
	}

	@Override
	public void reUpdateFileNot(RepsdVo vo) {
		  conn 	= DBConn.getConnection();
		  SQL = "update  repsd set "
		  		+ " sname =?, title=?, etc=? "
		  		+ " where idx=? ";
			 try {
				pstmt=  conn.prepareStatement(SQL);
				pstmt.setString(1, vo.getSname());
				pstmt.setString(2, vo.getTitle());
				pstmt.setString(3, vo.getEtc());
				pstmt.setInt(4,  vo.getIdx());				
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBConn.close(pstmt, conn);
			}	
		
	}

	@Override
	public int totalCount(RepsdVo vo) {
		int tc = 0; 
		 try {
			conn = DBConn.getConnection();
			if (vo.getCh1() == null || vo.getCh2().equals("")|| vo.getCh2().equals("null")) {
			  SQL = "select count(*) as tc  from repsd " ;  	
			  pstmt=  conn.prepareStatement(SQL);		
			} else {
			  SQL = "select count(*) as tc  from repsd where sname like ? " ;
			  pstmt=  conn.prepareStatement(SQL);	
			  pstmt.setString(1, "%"+vo.getCh2()+"%");
			}				
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				tc = rs.getInt("tc");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(rs, pstmt, conn);;
		}		  
	    return tc;

	}
	
	@Override
	public List<HashMap<String, Object>> getRePsdSelect(RepsdVo vo) {
		List<HashMap<String, Object>> li = new ArrayList<HashMap<String, Object>>();
		
			 try {
				conn = DBConn.getConnection();
				
				if (vo.getCh1() == null || vo.getCh2().equals("") || vo.getCh2().equals("null")  ) {
				  SQL = "select *  from repsd "
				  		+ " order  by ref desc ,  re_step asc  limit ?, ? " ;
				  pstmt=  conn.prepareStatement(SQL);
				  pstmt.setInt(1, vo.getSidx());
				  pstmt.setInt(2, vo.getPageSize());
				} else {
				  SQL = "select *  from repsd "
				  		+ " where sname like ? order  by ref desc ,  re_step asc "
				  		+ " limit ?, ? " ;
				  pstmt=  conn.prepareStatement(SQL);
				  pstmt.setString(1, "%" + vo.getCh2() + "%");
				  pstmt.setInt(2, vo.getSidx());
				  pstmt.setInt(3, vo.getPageSize());
				}
				rs = pstmt.executeQuery();
				HashMap<String, Object> m = null ;
				while(rs.next()) {
				 	m = new HashMap<String, Object>();
				 	m.put("idx", rs.getInt("idx"));
				 	m.put("sname", rs.getString("sname"));
				 	m.put("title", rs.getString("title"));
				 	m.put("img", rs.getString("img"));
					m.put("etc", rs.getString("etc"));
				 	m.put("cnt", rs.getInt("cnt"));
				 	m.put("ref", rs.getInt("ref"));
				 	m.put("re_step", rs.getInt("re_step"));
				 	m.put("re_level", rs.getInt("re_level"));					
				 	li.add(m);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBConn.close(rs, pstmt, conn);;
			}		  
		return li;
	}


}
