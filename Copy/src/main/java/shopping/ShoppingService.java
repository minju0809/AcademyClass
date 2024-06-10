package shopping;

import java.util.HashMap;
import java.util.List;

public interface ShoppingService {
   void  MemberInsert(MemberVO vo);
   List<HashMap<String, Object>>  getMemberSelect(MemberVO vo);
   List<MoneyVO>  getMoney();
   MemberVO  getMember(int custno);
   
}
