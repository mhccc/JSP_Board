package member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import member.model.MemberDTO;

public class MemberDAO {

	private static MemberDAO instance = new MemberDAO();
	public static MemberDAO getInstance() {
		return instance;
	}
	
	private MemberDAO() {
	}
	
	public void insert(Connection conn, MemberDTO member) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("insert into tbl_member (mem_userid, mem_password, mem_username, mem_email) values (?, ?, ?, ?)");
			pstmt.setString(1, member.getMem_userid());
			pstmt.setString(2, member.getMem_password());
			pstmt.setString(3, member.getMem_username());
			pstmt.setString(4, member.getMem_email());
			pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public MemberDTO selectById(Connection conn, String userid) throws SQLException {
		MemberDTO member = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("select * from tbl_member where mem_userid = ?");
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				member = new MemberDTO(
						rs.getString("mem_userid"),
						rs.getString("mem_password"),
						rs.getString("mem_username"),
						rs.getString("mem_email"),
						rs.getDate("mem_regdate"));
			}
			return member;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
}