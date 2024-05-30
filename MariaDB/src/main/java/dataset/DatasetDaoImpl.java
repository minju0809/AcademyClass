package dataset;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import pkg.DBConnection;

public class DatasetDaoImpl implements DatasetDao {

	DBConnection DBConn;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	DatasetDaoImpl() {
		DBConn = DBConnection.getInstance();
	}
	
	@Override
	public void insert(DatasetVO vo) {
		conn = DBConn.getConnection();
		String sql = "insert into dataT (idx,cnterNm,cnterChNm,lat,lot,hmpgAddr,roadNmAddr) "
				+ " values (?,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getIdx());
			pstmt.setString(2, vo.getCnterNm());
			pstmt.setString(3, vo.getCnterChNm());
			pstmt.setString(4, vo.getLat());
			pstmt.setString(5, vo.getLot());
			pstmt.setString(6, vo.getHmpgAddr());
			pstmt.setString(7, vo.getRoadNmAddr());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteAll() {
		conn = DBConn.getConnection();
		String sql = "delete from dataT";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<DatasetVO> selectAll() {
		List<DatasetVO> li = new ArrayList<>();
		conn = DBConn.getConnection();
		String sql = "select * from dataT";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				DatasetVO m = new DatasetVO();
				m.setIdx(rs.getInt("idx"));
				m.setCnterNm(rs.getString("cnterNm"));
				m.setCnterChNm(rs.getString("cnterChNm"));
				m.setLat(rs.getString("lat"));
				m.setLot(rs.getString("lot"));
				m.setHmpgAddr(rs.getString("hmpgAddr"));
				m.setRoadNmAddr(rs.getString("roadNmAddr"));
				li.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}

	@Override
	public DatasetVO selectOne(int idx) {
		DatasetVO m = new DatasetVO();
		conn = DBConn.getConnection();
		String sql = "select * from dataT where idx = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			while(rs.next()) {
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
