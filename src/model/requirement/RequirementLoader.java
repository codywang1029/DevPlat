package model.requirement;

import model.DataLoader;
import model.user.User;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RequirementLoader implements DataLoader<Requirement> {

	
	@Override
	public Requirement loadSingle(ResultSet rs) throws SQLException {
		Long id = rs.getLong("id");
		String name = rs.getString("name");
		String description = rs.getString("description");
		Integer stage = rs.getInt("stage");
		Long creatorId = rs.getLong("creator_id");
		Long engineerId = rs.getLong("engineer_id");
		Long reviewerId = rs.getLong("reviewer_id");
		Integer priority = rs.getInt("priority");
		Date engineerDeadline = rs.getDate("engineer_deadline");
		Date reviewerDeadline = rs.getDate("reviewer_deadline");
		Requirement requirement = new Requirement(id, name, description, stage, creatorId, engineerId, reviewerId, priority, engineerDeadline, reviewerDeadline);
		return requirement;
	}

	@Override
	public List<Requirement> loadList(ResultSet rs) throws SQLException {
		List<Requirement> requirements = new ArrayList<>();
	    while(rs.next()){
		    requirements.add(loadSingle(rs));
        }
        return requirements;
	}

}
