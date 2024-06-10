package psdBoard;

import java.util.List;

public class PsdServiceImpl implements PsdService {
	PsdDao dao =null;
	PsdServiceImpl(){
	   dao = new 	PsdDaoImpl();
		
	}
	
	@Override
	public void insert(PsdVo vo) {
		 dao.insert(vo);		
	}

	@Override
	public List<PsdVo> select() {
		// TODO Auto-generated method stub
		return dao.select();
	}

	@Override
	public PsdVo edit(int idx) {		
		return dao.edit(idx);
	}

	@Override
	public void delete(int idx) {
		dao.delete(idx);		
	}

	@Override
	public void updateFile(PsdVo vo) {
		dao.updateFile(vo);		
	}

	@Override
	public void update(PsdVo vo) {
		dao.update(vo);		
	}

}
