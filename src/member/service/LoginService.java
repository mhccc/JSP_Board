package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.ConnectionProvider;
import member.dao.MemberDAO;
import member.model.MemberDTO;

public class LoginService {

	private static LoginService instance = new LoginService();
	public static LoginService getInstance() {
		return instance;
	}
	
	private LoginService() {
		
	}
	
	private MemberDAO memberDAO = MemberDAO.getInstance();
	
	public User login(String mem_userid, String mem_password) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			MemberDTO member = memberDAO.selectById(conn, mem_userid);
			if (member == null) {
				throw new InvalidUseridException();
			} else if (!member.matchPassword(mem_password)) {
				throw new InvalidPasswordException();
			}
			
			return new User(member.getMem_userid(), member.getMem_username());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
}
