package model.reviewedReq;

import model.DataLoader;
import model.completedReq.CompletedRequirement;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewedRequirementLoader implements DataLoader<ReviewedReq> {

	
	@Override
	public ReviewedReq loadSingle(ResultSet rs) throws SQLException {
		Long requirementId = rs.getLong("requirement_id");
		Long engineerId = rs.getLong("reviewer_id");
		Date completedDate = rs.getDate("reviewed_date");
		String comment = rs.getString("comment");
		ReviewedReq completedRequirement = new ReviewedReq(requirementId,engineerId,comment,completedDate);
		return completedRequirement;
	}

	@Override
	public List<ReviewedReq> loadList(ResultSet rs) throws SQLException {
		List<ReviewedReq> requirements = new ArrayList<>();
	    while(rs.next()){
		    requirements.add(loadSingle(rs));
        }
        return requirements;
	}

}
