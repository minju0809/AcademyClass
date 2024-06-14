package product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import majustory.DBConnection;

public class ProductDaoImpl implements ProductDao {

	private DBConnection DBConn;
	
	private Connection conn ;
	private PreparedStatement pstmt ;
	private ResultSet rs = null;
	
	String SQL ;
	public ProductDaoImpl(){
		DBConn	= DBConnection.getInstance();
	}	
	
	@Override
	public void insert(ProductVO vo) {
		  conn 	= DBConn.getConnection();
		  SQL = " insert into product(pid,pname,pprice,pbaesongbi,pdesc,pimg)  "
		  		+ " values (?,?,?,?,?,?) ";
			 try {
				pstmt=  conn.prepareStatement(SQL);
				pstmt.setString(1, vo.getPid());
				pstmt.setString(2, vo.getPname());
				pstmt.setInt(3, vo.getPprice());
				pstmt.setInt(4, vo.getPbaesongbi());
				pstmt.setString(5,  vo.getPdesc());
				pstmt.setString(6,  vo.getPimg());		
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBConn.close(pstmt, conn);
			}		  
		
	}

	@Override
	public void update(ProductVO vo) {
		
	}

	@Override
	public void UpdateFileIn(ProductVO vo) {
		  conn 	= DBConn.getConnection();
		  SQL = " update product set pname=?,pprice=?,pbaesongbi=?,pdesc=? ,pimg=? "
		  		+ " where pid = ? ";
			 try {
				pstmt=  conn.prepareStatement(SQL);
				pstmt.setString(1, vo.getPname());
				pstmt.setInt(2, vo.getPprice());
				pstmt.setInt(3, vo.getPbaesongbi());
				pstmt.setString(4,  vo.getPdesc());
				pstmt.setString(5,  vo.getPimg());
				pstmt.setString(6, vo.getPid());				
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBConn.close(pstmt, conn);
			}		  
		
	}

	@Override
	public void UpdateFileNot(ProductVO vo) {
		  conn 	= DBConn.getConnection();
		  SQL = " update product set pname=?,pprice=?,pbaesongbi=?,pdesc=? "
		  		+ " where pid = ? ";
			 try {
				pstmt=  conn.prepareStatement(SQL);
				pstmt.setString(1, vo.getPname());
				pstmt.setInt(2, vo.getPprice());
				pstmt.setInt(3, vo.getPbaesongbi());
				pstmt.setString(4,  vo.getPdesc());
				pstmt.setString(5, vo.getPid());				
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBConn.close(pstmt, conn);
			}		  
	}

	@Override
	public void delete(ProductVO vo) {
		  conn 	= DBConn.getConnection();
		  SQL = " delete from product where pid =? ";		  		
			 try {
				pstmt=  conn.prepareStatement(SQL);
				pstmt.setString(1, vo.getPid());
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBConn.close(pstmt, conn);
			}		  
		
		
	}

	@Override
	public HashMap<String, Object> edit(ProductVO vo) {
		HashMap<String, Object> m = new HashMap<String, Object>(); 
		 try {
			conn = DBConn.getConnection();
			SQL = "select  *  from product where pid = ? " ;  		
			pstmt=  conn.prepareStatement(SQL);
			pstmt.setString(1, vo.getPid());
			rs = pstmt.executeQuery();			
			if(rs.next()) {
			 	m = new HashMap<String, Object>();
			 	m.put("pid", rs.getString("pid"));
			 	m.put("pname", rs.getString("pname"));
			 	m.put("pprice", rs.getInt("pprice"));
			 	m.put("pbaesongbi", rs.getInt("pbaesongbi"));
				m.put("pdesc", rs.getString("pdesc"));
			 	m.put("pimg", rs.getString("pimg"));		
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(rs, pstmt, conn);;
		}		  
	return m;
	}

	@Override
	public List<HashMap<String, Object>> select(ProductVO vo) {
		List<HashMap<String, Object>> li = new ArrayList<HashMap<String, Object>>();
		
		 try {
			conn = DBConn.getConnection();
			SQL = "select *  from product order  by pid  limit 0, 12" ;
			pstmt=  conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			HashMap<String, Object> m = null ;
			while(rs.next()) {
			 	m = new HashMap<String, Object>();
			 	m.put("pid", rs.getString("pid"));
			 	m.put("pname", rs.getString("pname"));
			 	m.put("pprice", rs.getInt("pprice"));
			 	m.put("pbaesongbi", rs.getInt("pbaesongbi"));
				m.put("pdesc", rs.getString("pdesc"));
			 	m.put("pimg", rs.getString("pimg"));					
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
