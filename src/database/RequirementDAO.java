package database;

import model.requirement.Requirement;
import model.requirement.RequirementLoader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RequirementDAO {

    private static final String TABLE = " devplat.requirement ";
    private static final String COLUMN = " name, description, stage, creator_id, engineer_id, reviewer_id, priority, engineer_deadline, reviewer_deadline ";
    private static final String COLUMN_FULL = " id, name, description, stage, creator_id, engineer_id, reviewer_id, priority, engineer_deadline, reviewer_deadline ";
    private DBConn connectionHandler;
    private RequirementLoader requirementLoader;

    public RequirementDAO(){
        connectionHandler = new DBConn();
        requirementLoader = new RequirementLoader();
    }

    public boolean insertReq(Requirement req) {
        Connection conn = connectionHandler.getConn();
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO" + TABLE + "(" + COLUMN + ")" + "VALUES(?,?,?,?,?,?,?,?,?);");
            stmt.setString(1, req.getName());
            stmt.setString(2, req.getDescription());
            stmt.setInt(3, req.getStage());
            stmt.setLong(4, req.getCreatorId());
            stmt.setLong(5, req.getEngineerId());
            stmt.setLong(6, req.getReviewerId());
            stmt.setInt(7, req.getPriority());
            stmt.setDate(8, req.getEngineerDeadline());
            stmt.setDate(9, req.getReviewerDeadline());
            int result = stmt.executeUpdate();
            return result == 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    public boolean deleteReq(Long id) {
        Connection conn = connectionHandler.getConn();
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM" + TABLE + "WHERE id=" + id);
            int result = stmt.executeUpdate();
            return result == 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean updateReq(Requirement req){
        Connection conn = connectionHandler.getConn();
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE " + TABLE + "SET id=?," +
                    "name=?," +
                    "description=?," +
                    "stage=?," +
                    "creator_id=?," +
                    "engineer_id=?," +
                    "reviewer_id=?," +
                    "priority=?," +
                    "engineer_deadline=?," +
                    "reviewer_deadline=?;");
            stmt.setLong(1, req.getId());
            stmt.setString(2, req.getName());
            stmt.setString(3, req.getDescription());
            stmt.setInt(4, req.getStage());
            stmt.setLong(5, req.getCreatorId());
            stmt.setLong(6, req.getEngineerId());
            stmt.setLong(7, req.getReviewerId());
            stmt.setInt(8, req.getPriority());
            stmt.setDate(9, req.getEngineerDeadline());
            stmt.setDate(10, req.getReviewerDeadline());
            int result = stmt.executeUpdate();
            return result == 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<Requirement> getRelatedReq(Long userId){
        Connection conn = connectionHandler.getConn();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT "+COLUMN_FULL+"FROM" + TABLE + "WHERE creator_id=? OR engineer_id=? OR reviewer_id=?;");
            ResultSet rs = stmt.executeQuery();
            List<Requirement> reqs = requirementLoader.loadList(rs);
            return reqs;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }


}
