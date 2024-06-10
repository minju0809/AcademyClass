package repsd;

import java.util.HashMap;
import java.util.List;

public interface RepsdService {
	  void rePsdInsert(RepsdVo vo);
	
	  HashMap<String, Object> getRePsdSelectOne(RepsdVo vo);
	  
	  int ref();
	  void reIsert(RepsdVo vo);
	  void cnt(int idx);
	  void reDelete(RepsdVo vo);  
	  
	  void reUpdateFileIn(RepsdVo vo);
	  void reUpdateFileNot(RepsdVo vo);
	
	  List<HashMap<String, Object>> getRePsdSelect(RepsdVo vo);
	  int totalCount(RepsdVo vo);
}
