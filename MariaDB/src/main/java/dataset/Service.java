package dataset;

import java.util.List;

public interface Service {
	void insert(DatasetVO vo);
	void deleteAll();
	List<DatasetVO> selectAll();
	DatasetVO selectOne(int idx);
	
	void stockDeleteAll();
	void stockInsert(StockVO vo);
	List<StockVO> stockSelectAll();
}
