package model.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.DataLoader;

public class UserLoader implements DataLoader<User> {

	
	@Override
	public User loadSingle(ResultSet rs) throws SQLException {
		Long id = rs.getLong("id");
		String username = rs.getString("username");
		String password = rs.getString("password");
		User user = new User(id,username,password);
		return user;
	}

	@Override
	public List<User> loadList(ResultSet rs) throws SQLException {
		return null;
	}

}
