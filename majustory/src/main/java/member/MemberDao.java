package member;

import java.util.List;

public interface MemberDao {
	void insert(MemberVO vo);
	void update(MemberVO vo);
	List<MemberVO> getSelect();
	MemberVO getSelectOne(MemberVO vo);
	
	String login(MemberVO vo);
}
