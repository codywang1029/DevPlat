package database;

import model.completedReq.CompletedRequirement;
import model.completedReq.CompletedRequirementLoader;
import model.requirement.Requirement;
import model.user.User;
import model.user.UserLoader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CompletedRequirementDAO {
	private static final String TABLE = " devplat.engineer_complete ";
	private static final String COLUMN = " requirement_id, engineer_id, comment, complete_date ";
	private DBConn connectionHandler;
	private CompletedRequirementLoader completedRequirementLoader;

	public CompletedRequirementDAO(){
		connectionHandler = new DBConn();
		completedRequirementLoader = new CompletedRequirementLoader();
	}

	/**
	 * insert a completed requirement
	 * @param completedRequirement
	 * @return true on success, false on failure
	 */
	public boolean insert(CompletedRequirement completedRequirement){
		Connection conn = connectionHandler.getConn();
		try {
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO" + TABLE + "(" + COLUMN + ")" + "VALUES(?,?,?,?);");
			stmt.setLong(1, completedRequirement.getRequirementId());
			stmt.setLong(2, completedRequirement.getEngineerId());
			stmt.setString(3,completedRequirement.getComment());
			stmt.setDate(4, completedRequirement.getCompletedDate());
			int result = stmt.executeUpdate();
			RequirementDAO requirementDAO = new RequirementDAO();
			Requirement req = requirementDAO.getRequirement(completedRequirement.getRequirementId());
			req.setStage(1);
			requirementDAO.updateReq(req);
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
	public CompletedRequirement get(Long id){
		Connection conn = connectionHandler.getConn();
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT "+COLUMN+"FROM" + TABLE + "WHERE requirement_id="+id+";");
			ResultSet rs = stmt.executeQuery();
			return completedRequirementLoader.loadList(rs).get(0);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

}
