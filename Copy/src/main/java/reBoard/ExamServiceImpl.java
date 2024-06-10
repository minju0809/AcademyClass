package reBoard;

import java.util.List;

public class ExamServiceImpl implements ExamService {
   
   private ExamDaoImpl dao ;
   
   public ExamServiceImpl() {
	  dao = new ExamDaoImpl();
   }

	@Override
	public List<ExamVo> getSelectAll() {
		return dao.getSelectAll();
	}

	@Override
	public ExamVo getSelectOne(String sno) {
		return dao.getSelectOne(sno);
	}

	@Override
	public  List<ReExamVo>  getReSelectAll(String sno) {
		return dao.getReSelectAll(sno);
	}

	@Override
	public void update(ExamVo vo) {
		dao.update(vo);		
	}

	@Override
	public void ReInsert(ReExamVo vo) {
		dao.ReInsert(vo);
		
	}

	@Override
	public void ReDelete(String idx) {
		dao.ReDelete(idx);
		
	}

	@Override
	public String ckID(String sno) {
		// TODO Auto-generated method stub
		return dao.ckID(sno);
	}

	@Override
	public void insert(ExamVo vo) {
		dao.insert(vo);
		
	}

}
