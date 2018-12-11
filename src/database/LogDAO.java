package database;

import model.log.Log;
import model.log.LogLoader;
import model.requirement.Requirement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LogDAO {
	private static final String TABLE = " devplat.log ";
	private static final String COLUMN = " id, user_id, requirement_id, action, log_time ";
	private DBConn connectionHandler;
	private LogLoader logLoader;

	public LogDAO() {
		connectionHandler = new DBConn();
		logLoader = new LogLoader();
	}

    /**
     * return requirement by id
     * @param userId
     * @return req
     */
    public List<Log> getLogForUser(Long userId){
        Connection conn = connectionHandler.getConn();
        try {
            List<Long> relevantReqId = new ArrayList<>();
            RequirementDAO requirementDao = new RequirementDAO();
            List<Requirement> reqs = requirementDao.getReqAsCreator(userId);
            for (Requirement req : reqs){
                relevantReqId.add(req.getId());
            }
            reqs = requirementDao.getReqAsReviwer(userId);
            for (Requirement req : reqs){
                relevantReqId.add(req.getId());
            }
            reqs = requirementDao.getReqAsEngineer(userId);
            for (Requirement req : reqs){
                relevantReqId.add(req.getId());
            }
            String sql = "SELECT "+COLUMN+"FROM" + TABLE + "WHERE ";
            for (Long reqId : relevantReqId){
                sql += "requirement_id = "+reqId.toString()+" or ";
            }
            sql+="1=1 ORDER BY log_time DESC;";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            return logLoader.loadList(rs);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean insertLog(Log log){
        Connection conn = connectionHandler.getConn();
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO" + TABLE + "(user_id, requirement_id, action, log_time)" + "VALUES(?,?,?,?);");
            stmt.setLong(1, log.getUserId());
            stmt.setLong(2, log.getRequirementId());
            stmt.setString(3, log.getAction());
            stmt.setTimestamp(4,log.getTime());
            int result = stmt.executeUpdate();
            return result==1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}