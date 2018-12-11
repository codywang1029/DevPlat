package model.log;

import model.DataLoader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class LogLoader implements DataLoader<Log> {

	
	@Override
	public Log loadSingle(ResultSet rs) throws SQLException {
		Long id = rs.getLong("id");

		Long userId = rs.getLong("user_id");
		Long requirementId = rs.getLong("requirement_id");
		String action = rs.getString("action");
		Timestamp time = rs.getTimestamp("log_time");

		Log log = new Log(id,userId,requirementId,action,time);
		return log;
	}

	@Override
	public List<Log> loadList(ResultSet rs) throws SQLException {
		List<Log> logs = new ArrayList<>();
	    while(rs.next()){
		    logs.add(loadSingle(rs));
        }
        return logs;
	}



}
