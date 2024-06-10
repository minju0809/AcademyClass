package shopping;

import java.util.HashMap;
import java.util.List;

public class ShoppingServiceImpl implements ShoppingService {

	private ShoppingDao dao = new ShoppingDaoImpl();
	
	public ShoppingServiceImpl(){}
	
	@Override
	public void MemberInsert(MemberVO vo) {
		dao.MemberInsert(vo);		
	}

	@Override
	public  List<HashMap<String, Object>> getMemberSelect(MemberVO vo) {
		return dao.getMemberSelect(vo);
	}

	@Override
	public List<MoneyVO> getMoney() {
		return dao.getMoney();
	}

	@Override
	public MemberVO getMember(int custno) {
		// TODO Auto-generated method stub
		return dao.getMember(custno);
	}

}
