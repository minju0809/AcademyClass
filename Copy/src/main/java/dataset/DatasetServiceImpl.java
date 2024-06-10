package dataset;

import java.util.HashMap;
import java.util.List;

public class DatasetServiceImpl implements DatasetService {

	DatasetDao dao =new DatasetDaoImpl();
	
	@Override
	public void insert(DataVo vo) {
		dao.insert(vo);
		
	}

	@Override
	public void deleteAll() {
		dao.deleteAll();
		
	}

	@Override
	public List<HashMap<String, Object>> selectAll() {		
		return dao.selectAll();
	}

	@Override
	public DataVo getEdit(DataVo vo) {
		return dao.getEdit(vo);
	}

}
