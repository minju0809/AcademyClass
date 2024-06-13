package majustory;

import java.util.List;

public class MajustoryServiceImpl implements MajustoryService {

	MemberDao dao;
	
	MajustoryServiceImpl() {
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

}
