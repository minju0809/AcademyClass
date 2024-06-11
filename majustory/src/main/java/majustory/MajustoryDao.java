package majustory;

import java.util.List;

public interface MajustoryDao {
	void insert(MajustoryVO vo);
	void update(MajustoryVO vo);
	List<MajustoryVO> getSelect();
	MajustoryVO getSelectOne(MajustoryVO vo);
}
