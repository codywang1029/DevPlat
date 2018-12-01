package model.completedReq;

import java.sql.Date;

public class CompletedRequirement {
    private Long requirementId;
    private Long engineerId;
    private String comment;
    private Date completedDate;

    public CompletedRequirement(Long requirementId, Long engineerId, String comment, Date completedDate) {
        this.requirementId = requirementId;
        this.engineerId = engineerId;
        this.comment = comment;
        this.completedDate = completedDate;
    }

    public Long getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(Long requirementId) {
        this.requirementId = requirementId;
    }

    public Long getEngineerId() {
        return engineerId;
    }

    public void setEngineerId(Long engineerId) {
        this.engineerId = engineerId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(Date completedDate) {
        this.completedDate = completedDate;
    }
}
