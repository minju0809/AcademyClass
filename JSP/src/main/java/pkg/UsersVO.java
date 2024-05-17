package pkg;

public class UsersVO {
	private String userid;
	private String username;
	private String userpassword;
	private int userage;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpassword() {
		return userpassword;
	}
	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}
	public int getUserage() {
		return userage;
	}
	public void setUserage(int userage) {
		this.userage = userage;
	}
	
	@Override
	public String toString() {
		return "UsersVO [userid=" + userid + ", username=" + username + ", userpassword=" + userpassword + ", userage="
				+ userage + "]";
	}
}
