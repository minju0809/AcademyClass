package cart;

import java.util.HashMap;
import java.util.List;

public class CartServiceImpl implements CartService {

	private CartDao dao = new CartDaoImpl();

	@Override
	public void insert(CartVO vo) {
		dao.insert(vo);
	}

	@Override
	public List<HashMap<String, Object>> cartSelect(CartVO vo) {
		return dao.cartSelect(vo);
	}

	@Override
	public void update(CartVO vo) {
		dao.update(vo);
	}

	@Override
	public void delete(CartVO vo) {
		dao.delete(vo);

	}

}
