package reBoard;

import lombok.Data;

@Data
public class ExamVo {	
  private  String sno;
  private  String sname;
  private  int kor;
  private  int eng;
  private  int math;
  private  int hist;
  private  String etc;  
}
