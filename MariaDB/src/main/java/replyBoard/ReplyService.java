package replyBoard;

import java.util.List;

public interface ReplyService {
	void insert(ReplyVO vo);
	
	void reInsert(ReplyVO vo);
	
	void update(ReplyVO vo);
	
	void delete(ReplyVO vo);
	
	List<ReplyVO> getSelect();
	
	ReplyVO getSelectOne(int idx);
	
	int maxRef();
}
