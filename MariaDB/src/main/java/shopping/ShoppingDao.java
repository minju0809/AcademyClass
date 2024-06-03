package shopping;

import java.util.HashMap;
import java.util.List;

public interface ShoppingDao {
	List<HashMap<String, Object>> getSelect(MemberVO vo);
	
	MemberVO getSelectOne(int custno);
	
	void insert(MemberVO vo);
	
	List<MoneyVO> getMoneySelect();
}
