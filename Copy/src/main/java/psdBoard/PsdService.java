package psdBoard;

import java.util.List;

public interface PsdService {
   void  insert(PsdVo vo);
   void  delete(int  idx);
   List<PsdVo>  select(); 
   PsdVo  edit( int idx ); 
   
   void  updateFile(PsdVo vo);
   void  update(PsdVo vo);
}
