package repsd;

import java.util.HashMap;
import java.util.List;

public interface RepsdDao {
  void rePsdInsert(RepsdVo vo);
  
  HashMap<String, Object> getRePsdSelectOne(RepsdVo vo);
  
  int ref();
  void reIsert(RepsdVo vo);
  void reDelete(RepsdVo vo);  
  
  void reUpdateFileIn(RepsdVo vo);
  void reUpdateFileNot(RepsdVo vo);
  
  void cnt(int idx);
  int totalCount(RepsdVo vo);
  List<HashMap<String, Object>> getRePsdSelect(RepsdVo vo);
}
