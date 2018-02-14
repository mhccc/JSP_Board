package member.service;

import java.util.Date;

public class User {
	
	private String mem_userid;
	private String mem_username;
	private Date mem_regdate;
	
	public User(String mem_userid, String mem_username, Date mem_regdate) {
		this.mem_userid = mem_userid;
		this.mem_username = mem_username;
		this.mem_regdate = mem_regdate;
	}
	
	public String getMem_userid() {
		return mem_userid;
	}
	public String getMem_username() {
		return mem_username;
	}
	public Date getMem_regdate() {
		return mem_regdate;
	}
	
}
