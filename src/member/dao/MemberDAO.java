package member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import member.dto.MemberDTO;

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
	
	public MemberDTO selectById(Connection conn, String mem_userid) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("select * from tbl_member where mem_userid = ?");
			pstmt.setString(1, mem_userid);
			rs = pstmt.executeQuery();
			MemberDTO member = null;
			if (rs.next()) {
				member = convertMember(rs);
			}
			return member;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	private MemberDTO convertMember(ResultSet rs) throws SQLException {
		return new MemberDTO(
				rs.getString("mem_userid"),
				rs.getString("mem_password"),
				rs.getString("mem_username"),
				rs.getString("mem_email"),
				rs.getDate("mem_regdate"));
	}
	
}
