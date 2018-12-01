package model.completedReq;

import model.DataLoader;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompletedRequirementLoader implements DataLoader<CompletedRequirement> {

	
	@Override
	public CompletedRequirement loadSingle(ResultSet rs) throws SQLException {
		Long requirementId = rs.getLong("requirement_id");
		Long engineerId = rs.getLong("engineer_id");
		Date completedDate = rs.getDate("complete_date");
		String comment = rs.getString("comment");
		CompletedRequirement completedRequirement = new CompletedRequirement(requirementId,engineerId,comment,completedDate);
		return completedRequirement;
	}

	@Override
	public List<CompletedRequirement> loadList(ResultSet rs) throws SQLException {
		List<CompletedRequirement> requirements = new ArrayList<>();
	    while(rs.next()){
		    requirements.add(loadSingle(rs));
        }
        return requirements;
	}

}
