package majustory;

import java.util.List;

public interface MajustoryService {
	void insert(MajustoryVO vo);
	void update(MajustoryVO vo);
	List<MajustoryVO> getSelect();
	MajustoryVO getSelectOne(MajustoryVO vo);
}
