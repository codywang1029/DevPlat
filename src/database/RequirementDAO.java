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

    /**
     * general insertion
     * @param req
     * @return true for success, false for failure
     */
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

    /**
     * delete a req based on name
     * @param id
     * @return true for success, false for failure
     */
    public boolean deleteReq(Long id) {
        Connection conn = connectionHandler.getConn();
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM" + TABLE + "WHERE id=" + id+";");
            int result = stmt.executeUpdate();
            return result == 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }


    /**
     * delete a req based on name
     * @param name
     * @return true for success, false for failure
     */
    public boolean deleteReq(String name) {
        Connection conn = connectionHandler.getConn();
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM" + TABLE + "WHERE name=\"" + name+"\";");
            int result = stmt.executeUpdate();
            return result == 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }


    /**
     * update a req based on
     * @param req
     * @return
     */
    public boolean updateReq(Requirement req){
        Connection conn = connectionHandler.getConn();
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE" + TABLE + "SET " +
                    "name=?," +
                    "description=?," +
                    "stage=?," +
                    "creator_id=?," +
                    "engineer_id=?," +
                    "reviewer_id=?," +
                    "priority=?," +
                    "engineer_deadline=?," +
                    "reviewer_deadline=? WHERE id = "+req.getId()+";");
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

    /**
     * return requirement by id
     * @param id
     * @return req
     */
    public Requirement getRequirement(Long id){
        Connection conn = connectionHandler.getConn();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT "+COLUMN_FULL+"FROM" + TABLE + "WHERE id="+id+";");
            ResultSet rs = stmt.executeQuery();
            return requirementLoader.loadList(rs).get(0);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


    /**
     * get req where user with userId is the creator
     * @param userId
     * @return list of req
     */
    public List<Requirement> getReqAsCreator(Long userId){
        Connection conn = connectionHandler.getConn();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT "+COLUMN_FULL+"FROM" + TABLE + "WHERE creator_id="+userId+";");
            ResultSet rs = stmt.executeQuery();
            return requirementLoader.loadList(rs);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * get req where user with userId is the engineer
     * @param userId
     * @return list of req
     */
    public List<Requirement> getReqAsEngineer(Long userId){
        Connection conn = connectionHandler.getConn();
        return getRequirements(userId, conn, "WHERE stage=0 AND engineer_id=");
    }

    private List<Requirement> getRequirements(Long userId, Connection conn, String s) {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT "+COLUMN_FULL+"FROM" + TABLE + s +userId+";");
            ResultSet rs = stmt.executeQuery();
            return requirementLoader.loadList(rs);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * get req where user with userId is the engineer
     * @param userId
     * @return list of req
     */
    public List<Requirement> getReqAsReviwer(Long userId){
        Connection conn = connectionHandler.getConn();
        return getRequirements(userId, conn, "WHERE stage=1 AND reviewer_id=");
    }


}
