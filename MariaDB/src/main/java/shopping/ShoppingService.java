package shopping;

import java.util.List;

public interface ShoppingService {
	List<MemberVO> getSelect(MemberVO vo);
	
	MemberVO getSelectOne(int custno);
	
	void insert(MemberVO vo);
	
	List<MoneyVO> getMoneySelect();
}