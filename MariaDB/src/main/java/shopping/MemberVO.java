package shopping;

import lombok.Data;

@Data
public class MemberVO {
	public MemberVO() {}
	
	public MemberVO(int custno, String custname, String phone, String address, String joindate, String grade, String city) {
	}

	private int custno;
	private String custname;
	private String phone;
	private String address;
	private String joindate;
	private String grade;
	private String city;
	
	private String latitude;
	private String longitude;
}
