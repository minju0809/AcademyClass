package psdBoard;

import java.util.List;

public class PsdServiceImpl implements PsdService {
	PsdDao dao = null;
	
	PsdServiceImpl() {
		dao = new PsdDaoImpl();
	}
	
	@Override
	public void insert(PsdVO vo) {
		dao.insert(vo);
	}

	@Override
	public List<PsdVO> getSelect(PsdVO vo) {
		return dao.getSelect(vo);
	}

	@Override
	public PsdVO getSelectOne(int idx) {
		return dao.getSelectOne(idx);
	}

	@Override
	public void delete(int idx) {
		dao.delete(idx);
	}

	@Override
	public void update(PsdVO vo) {
		dao.update(vo);
	}

	@Override
	public void updateFile(PsdVO vo) {
		dao.updateFile(vo);
	}

}
