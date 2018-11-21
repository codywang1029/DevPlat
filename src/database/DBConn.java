package database;


import java.sql.*;

public class DBConn {
	
	public DBConn() {
		
	}
	
	/**
	 * get connection to database
	 * @return connection
	 */
	public Connection getConn() {
		Connection conn=null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");  
			conn=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/devplat?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
			System.out.println("Successfully connected to database.");
		}
		catch(Exception e)
		{ 
			System.out.println("message "+e.getMessage());
		}  
		return conn;
	}
	
	/**
	 * close connection and prepared statement.
	 * @param conn
	 * @param ps
	 */
	public void closeConn(Connection conn,PreparedStatement ps) {
		try {
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
			System.out.println("Successfully closed connection.");
		} catch (SQLException e) {
			System.err.println("Error closing connections");
			e.printStackTrace();
		}
	}
}
