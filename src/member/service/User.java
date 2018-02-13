package member.service;

public class User {
	
	private String mem_userid;
	private String mem_username;
	
	public User(String mem_userid, String mem_username) {
		this.mem_userid = mem_userid;
		this.mem_username = mem_username;
	}
	
	public String getMem_userid() {
		return mem_userid;
	}
	
	public String getMem_username() {
		return mem_username;
	}
	
}
