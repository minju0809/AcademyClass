package majustory;

import java.util.List;

public interface MajustoryService {
	void insert(MemberVO vo);
	void update(MemberVO vo);
	List<MemberVO> getSelect();
	MemberVO getSelectOne(MemberVO vo);
}
