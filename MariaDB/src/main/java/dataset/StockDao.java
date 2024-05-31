package dataset;

import java.util.List;

public interface StockDao {
	void stockDeleteAll();
	void stockInsert(StockVO vo);
	List<StockVO> stockSelectAll();
}
