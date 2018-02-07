package jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionProvider {

	public static Connection getConnection() throws SQLException {
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:/comp/env/jdbc/mysql");
			return ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
}
