package provider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
	
	private static Connection conn;
	private static String jdbcDriver = "jdbc:apache:commons:dbcp:/pool";
	private static String USERNAME = "root";
	private static String PASSWORD = "hanbit";
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
		
		conn = DriverManager.getConnection(jdbcDriver);
		
		return conn;
	}
}
