package dataset;

import java.util.List;

public interface DatasetDao {
	void insert(DatasetVO vo);
	void deleteAll();
	List<DatasetVO> selectAll();
	DatasetVO selectOne(int idx);
}
