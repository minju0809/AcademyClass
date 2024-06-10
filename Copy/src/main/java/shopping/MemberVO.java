package shopping;

import lombok.Data;

@Data
public class MemberVO {
	 private  int  custno ;
	 private  String  custname ;
	 private  String  phone  ;
	 private  String  address  ;
	 private  String  joindate   ;
	 private  String  grade   ;
	 private  String  city   ;
	 
	 private  String  lat   ; // 위도
	 private  String  lon   ; // 경도 
	 
	 
	 MemberVO(){}
	 MemberVO(int custno,String custname,String phone,String address, String joindate, String grade ,String city ){
		this.custno = custno;
		this.custname = custname;
		this.phone = phone;
		this.address = address;
		this.joindate = joindate;
		this.grade = grade;
		this.city = city;		
	 }
}
