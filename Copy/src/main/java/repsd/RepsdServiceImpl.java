package repsd;

import java.util.HashMap;
import java.util.List;

public class RepsdServiceImpl implements RepsdService  {
	private RepsdDao dao ;
	public RepsdServiceImpl() {
		dao = new RepsdDaoImpl();
	}
	
	@Override
	public void rePsdInsert(RepsdVo vo) {
		dao.rePsdInsert(vo);
		
	}

	@Override
	public List<HashMap<String, Object>> getRePsdSelect(RepsdVo vo) {
		
		return dao.getRePsdSelect(vo);
	}

	@Override
	public int ref() {
		return dao.ref();
	}

	@Override
	public HashMap<String, Object> getRePsdSelectOne(RepsdVo vo) {
		return dao.getRePsdSelectOne(vo);
	}

	@Override
	public void reIsert(RepsdVo vo) {
		dao.reIsert(vo);
		
	}

	@Override
	public void cnt(int idx) {
		dao.cnt(idx);
		
	}

	@Override
	public void reDelete(RepsdVo vo) {
		dao.reDelete(vo);
		
	}

	@Override
	public void reUpdateFileIn(RepsdVo vo) {
		dao.reUpdateFileIn(vo);		
	}

	@Override
	public void reUpdateFileNot(RepsdVo vo) {
		dao.reUpdateFileNot(vo);		
	}

	@Override
	public int totalCount(RepsdVo vo) {
		// TODO Auto-generated method stub
		return dao.totalCount(vo);
	}

}
