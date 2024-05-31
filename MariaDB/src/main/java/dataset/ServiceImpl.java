package dataset;

import java.util.List;

public class ServiceImpl implements Service {
	DatasetDao dao;
	StockDao stockDao;
	
	public ServiceImpl() {
		dao = new DatasetDaoImpl();
		stockDao = new StockDaoImpl();
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
	

	@Override
	public void stockDeleteAll() {
		stockDao.stockDeleteAll();
	}
	@Override
	public void stockInsert(StockVO vo) {
		stockDao.stockInsert(vo);
	}

	@Override
	public List<StockVO> stockSelectAll() {
		return stockDao.stockSelectAll();
	}


}
