package majustory;

import java.util.List;

public class MajustoryServiceImpl implements MajustoryService {

	MajustoryDao dao;
	
	MajustoryServiceImpl() {
		dao = new MajustoryDaoImpl();
	}
	
	@Override
	public void insert(MajustoryVO vo) {
		dao.insert(vo);
	}
	
	@Override
	public void update(MajustoryVO vo) {
		dao.update(vo);
	}

	@Override
	public List<MajustoryVO> getSelect() {
		return dao.getSelect();
	}

	@Override
	public MajustoryVO getSelectOne(MajustoryVO vo) {
		return dao.getSelectOne(vo);
	}

}
