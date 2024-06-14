package member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface MemberService {
	
  public void insert(MemberVO vo);
  
  public List<HashMap<String, Object>> getSelectAll(MemberVO vo);
  
  public String loginOK(MemberVO vo);
  
  
}
