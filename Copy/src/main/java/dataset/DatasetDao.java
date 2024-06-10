package dataset;

import java.util.HashMap;
import java.util.List;

public interface DatasetDao {
  void  insert(DataVo vo);
  void  deleteAll();
  List<HashMap<String, Object>>  selectAll();
  DataVo  getEdit(DataVo vo);
}
