package psdBoard;

import java.util.List;

public interface PsdService {
	void insert(PsdVO vo);
	List<PsdVO> getSelect(PsdVO vo);
	PsdVO getSelectOne(int idx);
	void delete(int idx);
	void update(PsdVO vo);
	void updateFile(PsdVO vo);
}
