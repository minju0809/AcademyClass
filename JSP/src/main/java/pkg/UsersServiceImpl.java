package pkg;

import java.util.List;

public class UsersServiceImpl implements UsersService {
	
	private UsersDao dao = null;
	
	UsersServiceImpl() {
		dao = new UsersDaoImpl();
	}

	@Override
	public void insert(UsersVO vo) {
		dao.insert(vo);
	}

	@Override
	public List<UsersVO> getSelect() {
		return dao.getSelect();
	}

}
