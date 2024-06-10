package dataset;

import java.util.List;

public interface StockDao {
  void  insert(StockVO vo);
  void  delete();
  List<StockVO>  selectAll(StockVO vo);
}
