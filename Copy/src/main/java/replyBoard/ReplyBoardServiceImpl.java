package replyBoard;

import java.util.List;

public class ReplyBoardServiceImpl implements ReplyBoardService {
	ReplyBoardDao dao ;
	ReplyBoardServiceImpl(){
		dao = new ReplyBoardDaoImpl();
	}
	@Override
	public void insert(ReplyBoardVo vo) {
		dao.insert(vo);		
	}

	@Override
	public void reIsert(ReplyBoardVo vo) {
		dao.reIsert(vo);		
	}

	@Override
	public void update(ReplyBoardVo vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(ReplyBoardVo vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ReplyBoardVo getSelectOne(ReplyBoardVo vo) {
		return dao.getSelectOne(vo);
	}

	@Override
	public List<ReplyBoardVo> getSelectAll(ReplyBoardVo vo) {
		
		return dao.getSelectAll(vo);
	}

	@Override
	public int maxRef() {
		// TODO Auto-generated method stub
		return dao.maxRef();
	}

}
