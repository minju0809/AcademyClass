package dataset;

import java.util.List;

public interface StockService {
	  void  insert(StockVO vo);
	  void  delete();
	  List<StockVO>  selectAll(StockVO vo);
}
