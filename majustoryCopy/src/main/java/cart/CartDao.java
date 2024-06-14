package cart;

import java.util.HashMap;
import java.util.List;

public interface CartDao {
   void  insert(CartVO vo);
   void  update(CartVO vo);
   void  delete(CartVO vo);
   List<HashMap<String, Object>>  cartSelect(CartVO vo);
}
