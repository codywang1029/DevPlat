package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface DataLoader<T> {
	
	/**
	 * load one single T from the result set
	 * @param rs
	 * @return Object T
	 * @throws SQLException
	 */
	public T loadSingle(ResultSet rs) throws SQLException;
	
	
	/**
	 * load a list of T from result set
	 * @param rs
	 * @return a list of Object T
	 * @throws SQLException
	 */
	public List<T> loadList(ResultSet rs) throws SQLException;
}
