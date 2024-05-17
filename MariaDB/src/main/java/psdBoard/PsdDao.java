package psdBoard;

import java.util.List;

public interface PsdDao {
	void insert(PsdVO vo);
	List<PsdVO> getSelect(PsdVO vo);
	PsdVO getSelectOne(int idx);
	void delete(int idx);
	void update(PsdVO vo);
	void updateFile(PsdVO vo);
}
