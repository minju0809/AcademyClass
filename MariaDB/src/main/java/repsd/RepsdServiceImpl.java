package repsd;

import java.util.List;

public class RepsdServiceImpl implements RepsdService {

	RepsdDao dao;
		
	public RepsdServiceImpl() {
		dao = new RepsdDaoImpl();
	}
	
	@Override
	public void insert(RepsdVO vo) {
		dao.insert(vo);
	}

	@Override
	public List<RepsdVO> getSelect(RepsdVO vo) {
		return dao.getSelect(vo);
	}

	@Override
	public int ref() {
		return dao.ref();
	}

	@Override
	public RepsdVO getSelectOne(RepsdVO vo) {
		return dao.getSelectOne(vo);
	}

	@Override
	public void reInsert(RepsdVO vo) {
		dao.reInsert(vo);
	}

	@Override
	public int cnt(int idx) {
		return dao.cnt(idx);
	}

	@Override
	public void delete(int idx) {
		dao.delete(idx);
	}

	@Override
	public void update(RepsdVO vo) {
		dao.update(vo);
	}

	@Override
	public void updateFile(RepsdVO vo) {
		dao.updateFile(vo);
	}

	@Override
	public int totalCount(RepsdVO vo) {
		return dao.totalCount(vo);
	}

}
