package summernote;

import java.util.List;
import java.util.Map;

public interface SummernoteDao {
   void insert(SummerVO vo);
   void update(SummerVO vo);
   void delete(SummerVO vo);
   
   List<Map<String, Object>>  select(SummerVO vo);
   Map<String, Object> edit(SummerVO vo);
}
