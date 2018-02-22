package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import member.dao.MemberDAO;
import member.dto.MemberDTO;

public class JoinService {

	private static JoinService instance = new JoinService();
	public static JoinService getInstance() {
		return instance;
	}
	private JoinService() {
		
	}
	
	private MemberDAO memberDAO = MemberDAO.getInstance();
	
	public void join(MemberDTO member) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			memberDAO.insert(conn, member);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	public String joinCheck(String userid) {
		Connection conn = null;
		String result = "success";
		try {
			conn = ConnectionProvider.getConnection();
			MemberDTO member = memberDAO.selectById(conn, userid);
			if (member != null) {
				result = "duplicate";
			}
			return result;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
}
