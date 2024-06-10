package dataset;

import java.util.HashMap;
import java.util.List;

public interface DatasetService {
	  void  insert(DataVo vo);
	  void  deleteAll();
	  List<HashMap<String, Object>>  selectAll();
	  DataVo  getEdit(DataVo vo);
}
