package reBoard;

import java.util.List;

public interface ExamDao {
	List<ExamVO> getSelect();
	
	ExamVO getSelectOne(String sno);
	
	String ckID(String sno);
	
	void insert(ExamVO vo);
	
	
	List<ReExamVO> getReExamSelect(String sno);
	
	void reExamInsert(ReExamVO vo);
	
	void reExamDelete(int idx);
}
