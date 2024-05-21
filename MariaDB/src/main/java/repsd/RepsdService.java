package repsd;

import java.util.List;

public interface RepsdService {
	void insert(RepsdVO vo);
	List<RepsdVO> getSelect(RepsdVO vo);
	RepsdVO getSelectOne(RepsdVO vo);
	
	int ref();
	
	void reInsert(RepsdVO vo);
	
	int cnt(int idx);
	
	void delete(int idx);
}
