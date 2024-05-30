package dataset;

import java.util.List;

public class DatasetServiceImpl implements DatasetService {
	DatasetDao dao;
	
	public DatasetServiceImpl() {
		dao = new DatasetDaoImpl();
	}
	
	@Override
	public void insert(DatasetVO vo) {
		dao.insert(vo);
	}

	@Override
	public void deleteAll() {
		dao.deleteAll();
	}

	@Override
	public List<DatasetVO> selectAll() {
		return dao.selectAll();
	}

	@Override
	public DatasetVO selectOne(int idx) {
		return dao.selectOne(idx);
	}

}
