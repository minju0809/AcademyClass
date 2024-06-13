package product;

import java.util.HashMap;
import java.util.List;

public class ProductServiceImpl implements ProductService  {

	ProductDao dao = new ProductDaoImpl();
	
	@Override
	public void insert(ProductVO vo) {
		dao.insert(vo);		
	}

	@Override
	public void update(ProductVO vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(ProductVO vo) {
		dao.delete(vo);
		
	}

	@Override
	public HashMap<String, Object> edit(ProductVO vo) {
		
		return dao.edit(vo);
	}

	@Override
	public List<HashMap<String, Object>> select(ProductVO vo) {
		
		return dao.select(vo);
	}

	@Override
	public void UpdateFileIn(ProductVO vo) {
		dao.UpdateFileIn(vo);		
	}

	@Override
	public void UpdateFileNot(ProductVO vo) {
		dao.UpdateFileNot(vo);
		
	}

}
