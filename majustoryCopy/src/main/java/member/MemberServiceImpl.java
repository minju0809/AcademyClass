package member;

import java.util.HashMap;
import java.util.List;

public class MemberServiceImpl implements  MemberService {

	MemberDao dao =new MemberDaoImpl();
	
	@Override
	public void insert(MemberVO vo) {
		dao.insert(vo);
		
	}

	@Override
	public List<HashMap<String, Object>> getSelectAll(MemberVO vo) {		
		return dao.getSelectAll(vo);
	}

	@Override
	public String loginOK(MemberVO vo) {

		return dao.loginOK(vo);
	}

}
