package product;

import java.util.HashMap;
import java.util.List;

public interface ProductService {
	
   void  insert(ProductVO vo);
   void  update(ProductVO vo);
   void  UpdateFileIn(ProductVO vo);
   void  UpdateFileNot(ProductVO vo);
   
   void  delete(ProductVO vo);
   
   HashMap<String, Object>  edit(ProductVO vo);
   List<HashMap<String, Object>>  select(ProductVO vo);
   
}
