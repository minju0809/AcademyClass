package member;

import java.util.List;

public class MemberServiceImpl implements MemberService {

	MemberDao dao;
	
	MemberServiceImpl() {
		dao = new MemberDaoImpl();
	}
	
	@Override
	public void insert(MemberVO vo) {
		dao.insert(vo);
	}
	
	@Override
	public void update(MemberVO vo) {
		dao.update(vo);
	}

	@Override
	public List<MemberVO> getSelect() {
		return dao.getSelect();
	}

	@Override
	public MemberVO getSelectOne(MemberVO vo) {
		return dao.getSelectOne(vo);
	}

	@Override
	public String login(MemberVO vo) {
		return dao.login(vo);
	}

}
