package reBoard;

import java.util.List;

public interface ExamService {
  String  ckID(String sno);
  void  update(ExamVo vo);
  void  ReInsert(ReExamVo vo);
  void  ReDelete(String idx);
  
  void  insert(ExamVo vo);
  
  List<ExamVo> getSelectAll();
  ExamVo getSelectOne(String sno);
  
  List<ReExamVo>  getReSelectAll(String sno);
  
}
