package database;

import model.log.Log;
import model.requirement.Requirement;
import model.reviewedReq.ReviewedReq;
import model.reviewedReq.ReviewedRequirementLoader;
import model.user.CurrentUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewedRequirementDAO {
	private static final String TABLE = " devplat.reviewer_complete ";
	private static final String COLUMN = " requirement_id, reviewer_id, comment, reviewed_date ";
	private DBConn connectionHandler;
	private ReviewedRequirementLoader reviewedRequirementLoader;

	public ReviewedRequirementDAO(){
		connectionHandler = new DBConn();
		reviewedRequirementLoader = new ReviewedRequirementLoader();
	}

	/**
	 * insert a reviewed requirement
	 * @param reviewedReq
	 * @return true on success, false on failure
	 */
	public boolean insert(ReviewedReq reviewedReq){
		Connection conn = connectionHandler.getConn();
		try {
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO" + TABLE + "(" + COLUMN + ")" + "VALUES(?,?,?,?);");
			stmt.setLong(1, reviewedReq.getRequirementId());
			stmt.setLong(2, reviewedReq.getReviewedId());
			stmt.setString(3,reviewedReq.getComment());
			stmt.setDate(4, reviewedReq.getReviewedDate());
			int result = stmt.executeUpdate();
			RequirementDAO requirementDAO = new RequirementDAO();
			Requirement req = requirementDAO.getRequirement(reviewedReq.getRequirementId());
			req.setStage(2);
			requirementDAO.updateReq(req);

			CurrentUser currentUser = CurrentUser.getInstance();
			LogDAO logDAO = new LogDAO();
			Log log= new Log(null,currentUser.id,reviewedReq.getRequirementId(),"Reviewed");
			logDAO.insertLog(log);

			return result == 1;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	/**
	 * return completed requirement by id
	 * @param id
	 * @return completed requirement
	 */
	public ReviewedReq get(Long id){
		Connection conn = connectionHandler.getConn();
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT "+COLUMN+"FROM" + TABLE + "WHERE requirement_id="+id+";");
			ResultSet rs = stmt.executeQuery();
			return reviewedRequirementLoader.loadList(rs).get(0);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

}
