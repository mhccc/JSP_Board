package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import member.dao.MemberDAO;
import member.dto.MemberDTO;

public class PrivacyService {
	
	private static PrivacyService instance = new PrivacyService();
	public static PrivacyService getInstance() {
		return instance;
	}
	private PrivacyService() {
		
	}
	
	private MemberDAO memberDAO = MemberDAO.getInstance();
	
	public MemberDTO getPrivacy(String mem_userid) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			return memberDAO.selectById(conn, mem_userid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
}
