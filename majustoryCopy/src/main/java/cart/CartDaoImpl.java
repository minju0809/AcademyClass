package cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import majustory.DBConnection;

public class CartDaoImpl implements CartDao {

	private DBConnection DBConn = DBConnection.getInstance();
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	@Override
	public void insert(CartVO vo) {
		System.out.println("CartVO vo확인:" + vo);
		try {
			conn = DBConn.getConnection();
			String SQL = "INSERT INTO tbl_cart " + "( mid, pid, amount )" + " VALUES (?,?,?) ";

			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, vo.getMid());
			pstmt.setString(2, vo.getPid());
			pstmt.setInt(3, vo.getAmount());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<HashMap<String, Object>> cartSelect(CartVO vo) {

		List<HashMap<String, Object>> li = new ArrayList<HashMap<String, Object>>();
		try {
			conn = DBConn.getConnection();
			String SQL = "select  p.pid as pid, pname, pprice, pimg, cart_id, mid, amount from  product  p  join tbl_cart c "
					+ " on p.pid = c.pid where  mid = ? ";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, vo.getMid());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				HashMap<String, Object> member = new HashMap<String, Object>();
				member.put("cart_id", rs.getString("cart_id"));
				member.put("mid", rs.getString("mid"));
				member.put("pid", rs.getString("pid"));
				member.put("pname", rs.getString("pname"));
				member.put("pimg", rs.getString("pimg"));
				member.put("pprice", rs.getInt("pprice"));
				member.put("amount", rs.getInt("amount"));
				li.add(member);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return li;
	}

	@Override
	public void update(CartVO vo) {
		System.out.println("CartVO vo확인:" + vo);
		try {
			conn = DBConn.getConnection();
			String SQL = "Update tbl_cart set  amount =? " + " where cart_id = ? ";

			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, vo.getAmount());
			pstmt.setString(2, vo.getCart_id());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void delete(CartVO vo) {
		System.out.println("CartVO vo확인:" + vo);
		try {
			conn = DBConn.getConnection();
			String SQL = "delete from tbl_cart " + " where mid = ? ";
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, vo.getMid());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
