package replyBoard;

import lombok.Data;

@Data
public class ReplyBoardVo {
   private int  idx;
   private String   sname;
   private String   title;
   private int  cnt;
   private int  ref;
   private int  re_step;
   private int  re_level;
}
