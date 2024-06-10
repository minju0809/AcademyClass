package ckeditor;

import java.util.List;
import java.util.Map;

public interface CkeditorDao {
	void insert(CkeditorVO vo);
	void update(CkeditorVO vo);
	void delete(CkeditorVO vo);
	  
	List<Map<String, Object>>  select(CkeditorVO vo);
	Map<String, Object> edit(CkeditorVO vo);
	
	void commentInsert(CommentVO vo);
	void commentDelete(String cidx);
	
	List<Map<String, Object>>  commentSelect(CommentVO vo);
}
