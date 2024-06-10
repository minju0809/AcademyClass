package dataset;

import java.util.List;

public class StockServiceImpl  implements  StockService {

	StockDao dao = new StockDaoImpl();
	
	@Override
	public void insert(StockVO vo) {
		dao.insert(vo);		
	}

	@Override
	public List<StockVO> selectAll(StockVO vo) {		
		return dao.selectAll(vo);
	}

	@Override
	public void delete() {
		dao.delete();
		
	}

}
