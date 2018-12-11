package model.log;

import java.sql.Timestamp;

public class Log {
    private Long id;
    private Long userId;
    private Long requirementId;
    String action;
    private Timestamp time;


    public Log(Long id, Long userId, Long requirementId, String action) {
        this.id = id;
        this.userId = userId;
        this.requirementId = requirementId;
        this.action=action;
        this.time = new Timestamp(System.currentTimeMillis());
    }


    public Log(Long id, Long userId, Long requirementId, String action, Timestamp time) {
        this.id = id;
        this.userId = userId;
        this.requirementId = requirementId;
        this.action = action;
        this.time = time;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(Long requirementId) {
        this.requirementId = requirementId;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
