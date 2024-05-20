package replyBoard;

import java.util.List;

public class ReplyServiceImpl implements ReplyService {

	ReplyDao dao;
	
	ReplyServiceImpl() {
		dao = new ReplyDaoImpl();
	}
	
	@Override
	public void insert(ReplyVO vo) {
		dao.insert(vo);
	}

	@Override
	public void reInsert(ReplyVO vo) {
		dao.reInsert(vo);
	}

	@Override
	public void update(ReplyVO vo) {
		dao.update(vo);
	}

	@Override
	public void delete(ReplyVO vo) {
		dao.delete(vo);
	}

	@Override
	public List<ReplyVO> getSelect() {
		return dao.getSelect();
	}

	@Override
	public ReplyVO getSelectOne(int idx) {
		return dao.getSelectOne(idx);
	}

	@Override
	public int maxRef() {
		return dao.maxRef();
	}

}
