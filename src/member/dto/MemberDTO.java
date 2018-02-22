package member.dto;

import java.util.Date;

public class MemberDTO {

	private String mem_userid;
	private String mem_password;
	private String mem_username;
	private String mem_email;
	private Date mem_regdate;
	
	public MemberDTO() {
		
	}
	
	public MemberDTO(String mem_userid, String mem_password, String mem_username, String mem_email, Date mem_regdate) {
		this.mem_userid = mem_userid;
		this.mem_password = mem_password;
		this.mem_username = mem_username;
		this.mem_email = mem_email;
		this.mem_regdate = mem_regdate;
	}

	public String getMem_userid() {
		return mem_userid;
	}
	public void setMem_userid(String mem_userid) {
		this.mem_userid = mem_userid;
	}
	public String getMem_password() {
		return mem_password;
	}
	public void setMem_password(String mem_password) {
		this.mem_password = mem_password;
	}
	public String getMem_username() {
		return mem_username;
	}
	public void setMem_username(String mem_username) {
		this.mem_username = mem_username;
	}
	public String getMem_email() {
		return mem_email;
	}
	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}
	public Date getMem_regdate() {
		return mem_regdate;
	}
	public void setMem_regdate(Date mem_regdate) {
		this.mem_regdate = mem_regdate;
	}

	@Override
	public String toString() {
		return "Member [mem_userid=" + mem_userid + ", mem_password=" + mem_password + ", mem_username=" + mem_username
				+ ", mem_email=" + mem_email + ", mem_regdate=" + mem_regdate + "]";
	}
	
	public boolean matchPassword(String mem_password) {
		return this.mem_password.equals(mem_password);
	}
	
}
