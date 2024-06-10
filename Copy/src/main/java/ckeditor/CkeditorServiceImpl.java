package ckeditor;

import java.util.List;
import java.util.Map;

public class CkeditorServiceImpl implements CkeditorService {
	
	private CkeditorDao dao;
	
	CkeditorServiceImpl() {
		dao = new CkeditorDaoImpl();
	}

	@Override
	public void insert(CkeditorVO vo) {
		dao.insert(vo);
	}

	@Override
	public void update(CkeditorVO vo) {
		dao.update(vo);
	}

	@Override
	public void delete(CkeditorVO vo) {
		dao.delete(vo);
	}

	@Override
	public List<Map<String, Object>> select(CkeditorVO vo) {
		return dao.select(vo);
	}

	@Override
	public Map<String, Object> edit(CkeditorVO vo) {
		return dao.edit(vo);
	}

	@Override
	public void commentInsert(CommentVO vo) {
		dao.commentInsert(vo);
	}

	@Override
	public void commentDelete(String cidx) {
		dao.commentDelete(cidx);
	}

	@Override
	public List<Map<String, Object>> commentSelect(CommentVO vo) {
		return dao.commentSelect(vo);
	}

}
