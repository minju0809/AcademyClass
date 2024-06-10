package dataset;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import pkg.DBConnection;
import reBoard.ExamVo;

public class DatasetDaoImpl implements DatasetDao {
	DBConnection DBConn ;
	
	DatasetDaoImpl(){
		 DBConn = DBConnection.getInstance();
	}
	
	private Connection conn ;
	private PreparedStatement pstmt;
	private ResultSet rs ;
	
	@Override
	public void insert(DataVo vo) {
		conn = DBConn.getConnection();	
						
		String SQL = "insert  into dataT "
				+ "(idx, cnterNm, cnterChNm, roadNmAddr, lat, lot, hmpgAddr ) "
				+ " values(?,?,?,?,?,?,?)  ";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, vo.getIdx());
			pstmt.setString(2, vo.getCnterNm());
			pstmt.setString(3, vo.getCnterChNm());
			pstmt.setString(4, vo.getRoadNmAddr());	
			pstmt.setString(5, vo.getLat());	
			pstmt.setString(6, vo.getLot());
			pstmt.setString(7, vo.getHmpgAddr());	
			pstmt.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
				
	}

	@Override
	public void deleteAll() {
		conn = DBConn.getConnection();		
		String SQL = "delete from dataT " ;
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		
	}

	@Override
	public List<HashMap<String, Object>> selectAll() {
		List<HashMap<String, Object>> li = new ArrayList<HashMap<String, Object>>();
		
		conn = DBConn.getConnection();		
		String SQL = "select  *  from  dataT";
		try {
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				  HashMap<String, Object> map = new HashMap<String, Object>();
			        map.put("idx", rs.getInt("idx"));
			        map.put("cnterNm", rs.getString("cnterNm"));
			        map.put("cnterChNm", rs.getString("cnterChNm"));
			        map.put("lat", rs.getString("lat"));
			        map.put("lot", rs.getString("lot"));
			        map.put("hmpgAddr", rs.getString("hmpgAddr"));
			        map.put("roadNmAddr", rs.getString("roadNmAddr"));
			        System.out.println("====> HashMap ");
			        li.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return li;
	}

	@Override
	public DataVo getEdit(DataVo vo) {
		
		DataVo m = new DataVo();
		
		conn = DBConn.getConnection();		
		String SQL = "select  *  from  dataT where idx = ?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, vo.getIdx());
			rs = pstmt.executeQuery();
			if(rs.next()) {				
				m.setIdx(rs.getInt("idx"));
				m.setCnterNm(rs.getString("cnterNm"));
				m.setCnterChNm(rs.getString("cnterChNm"));
				m.setLat(rs.getString("lat"));
				m.setLot(rs.getString("lot"));
				m.setHmpgAddr(rs.getString("hmpgAddr"));
				m.setRoadNmAddr(rs.getString("roadNmAddr"));	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return m;
	}

}
