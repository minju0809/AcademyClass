package member;

import java.util.HashMap;
import java.util.List;


public interface MemberDao {
	  public void insert(MemberVO vo);
	  
	  public String loginOK(MemberVO vo);
	  
	  public List<HashMap<String, Object>> getSelectAll(MemberVO vo);
}
