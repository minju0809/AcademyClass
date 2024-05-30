package dataset;

import java.util.List;

public interface DatasetService {
	void insert(DatasetVO vo);
	void deleteAll();
	List<DatasetVO> selectAll();
	DatasetVO selectOne(int idx);
}
