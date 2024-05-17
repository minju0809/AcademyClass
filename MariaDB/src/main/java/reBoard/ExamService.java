package reBoard;

import java.util.List;

public interface ExamService {
	List<ExamVO> getSelect();
	
	ExamVO getSelectOne(String sno);
	
	List<ReExamVO> getReExamSelect(String sno);
	
	void reExamInsert(ReExamVO vo);
}
