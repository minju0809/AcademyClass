package reBoard;

import java.util.List;

public class ExamServiceImpl implements ExamService {
	
	ExamDao dao = null;
	
	ExamServiceImpl() {
		dao = new ExamDaoImpl();
	}
	
	@Override
	public List<ExamVO> getSelect() {
		return dao.getSelect();
	}

	@Override
	public ExamVO getSelectOne(String sno) {
		return dao.getSelectOne(sno);
	}

	@Override
	public List<ReExamVO> getReExamSelect(String sno) {
		return dao.getReExamSelect(sno);
	}

	@Override
	public void reExamInsert(ReExamVO vo) {
		dao.reExamInsert(vo);
	}

}
