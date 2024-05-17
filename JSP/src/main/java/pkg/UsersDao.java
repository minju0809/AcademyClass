package pkg;

import java.util.List;

public interface UsersDao {
	void insert(UsersVO vo);
	List<UsersVO> getSelect();
}
