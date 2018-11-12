package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.user.User;
import model.user.UserLoader;

public class UserDAO {
	private static final String TABLE = " devplat.user ";
	private static final String COLUMN = " id, username, password ";
	private DBConn connectionHandler;
	private UserLoader userLoader;
	
	public UserDAO(){
		connectionHandler = new DBConn();
		userLoader = new UserLoader();
	}
	
	
	/**
	 * identify if user is in the database and perform login.
	 * @param user  a User object containing username and password
	 * @return true if successfully logged in, false otherwise.
	 * @throws SQLException
	 */
	public User login(User user) throws SQLException{
		Connection conn = connectionHandler.getConn();
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT"+COLUMN+"FROM"+TABLE+"WHERE username=\""+user.getUsername()+"\" AND password=\""+user.getPassword()+"\";");
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				User loginUser = userLoader.loadSingle(rs);
				connectionHandler.closeConn(conn, stmt);
				return loginUser;
			}
			else {
				connectionHandler.closeConn(conn, stmt);
				return null;
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * insert user into the database
	 * @param user  a User object containing username and password
	 * @return true if register is successful, false otherwise.
	 * @throws SQLException
	 */
	public boolean register(User user) throws SQLException {
		Connection conn = connectionHandler.getConn();
		try {
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO"+TABLE+"(username,password) VALUES(\""+user.getUsername()+"\",\""+user.getPassword()+"\")");
			int rows = stmt.executeUpdate();
			connectionHandler.closeConn(conn, stmt);
			return true;
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	/**
	 * Check if username is occupied
	 * @param username
	 * @return true if username is occupied, false otherwise.
	 * @throws SQLException
	 */
	public boolean existUser(String username) throws SQLException {
		Connection conn = connectionHandler.getConn();
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM"+TABLE+"WHERE username=\""+username+"\";");
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				connectionHandler.closeConn(conn, stmt);
				return true;
			}
			else {
				connectionHandler.closeConn(conn, stmt);
				return false;
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
}
