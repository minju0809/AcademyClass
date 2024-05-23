package repsd;

import lombok.Data;

@Data
public class RepsdVO {
	private int idx;
	private String sname;
	private String title;
	private String img;
	private String etc;
	private int cnt;
	private int ref;
	private int re_step;
	private int re_level;
	
	private String ch1;
	private String ch2;
	
	private int start;
	private int pageSize;
}
