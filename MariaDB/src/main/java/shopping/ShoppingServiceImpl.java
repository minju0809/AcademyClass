package shopping;

import java.util.HashMap;
import java.util.List;

public class ShoppingServiceImpl implements ShoppingService {
	ShoppingDao dao;
	
	public ShoppingServiceImpl() {
		dao = new ShoppingDaoImpl();
	}

	@Override
	public List<HashMap<String, Object>> getSelect(MemberVO vo) {
		return dao.getSelect(vo);
	}
	
	@Override
	public MemberVO getSelectOne(int custno) {
		return dao.getSelectOne(custno);
	}

	@Override
	public void insert(MemberVO vo) {
		dao.insert(vo);
	}

	@Override
	public List<MoneyVO> getMoneySelect() {
		return dao.getMoneySelect();
	}
	
}
