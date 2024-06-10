package login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pkg.DBConnection;
import reBoard.ExamVo;

public class LoginDaoImpl implements  LoginDao{
	
	DBConnection DBConn ;
	private Connection conn ;
	private PreparedStatement pstmt;
	private ResultSet rs ;
	
	LoginDaoImpl(){
		 DBConn = DBConnection.getInstance();	
	}
	
	@Override
	public String login(LoginVO vo) {
		String m = "";
		
		conn = DBConn.getConnection();		
		String SQL = "select  *  from  admin where id=? and password =? ";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPassword());
			rs = pstmt.executeQuery();
			if(rs.next()) {				
				m= rs.getString("id");				
			}else {
				m ="";				
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return m;
	}

}
