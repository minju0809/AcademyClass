package summernote;

import java.util.List;
import java.util.Map;

public class SummernoteServiceImpl implements SummernoteService {

	SummernoteDao dao = new SummernoteDaoImpl();
	
	@Override
	public void insert(SummerVO vo) {
		dao.insert(vo);		
	}

	@Override
	public void update(SummerVO vo) {
		dao.update(vo);
	}

	@Override
	public void delete(SummerVO vo) {
		dao.delete(vo);
	}
	
	@Override
	public List<Map<String, Object>> select(SummerVO vo) {		
		return dao.select(vo);
	}

	@Override
	public Map<String, Object> edit(SummerVO vo) {
		return dao.edit(vo);
	}
}
