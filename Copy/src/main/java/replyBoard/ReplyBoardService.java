package replyBoard;

import java.util.List;

public interface ReplyBoardService {
	   void  insert( ReplyBoardVo vo );  // 새글작성
	   void  reIsert( ReplyBoardVo vo ); // 답글작성
	   void  update( ReplyBoardVo vo );
	   void  delete( ReplyBoardVo vo );
	   
	   int  maxRef();  //  ref 가져오기
	   
	   ReplyBoardVo  getSelectOne( ReplyBoardVo vo );
	   List<ReplyBoardVo>  getSelectAll( ReplyBoardVo vo );   
}
