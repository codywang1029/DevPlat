package model.requirement;

import java.sql.Date;

public class Requirement {
    private Long id;
    private String name;
    private String description;
    private Integer stage;
    private Long creatorId;
    private Long engineerId;
    private Long reviewerId;
    private Integer priority;
    private Date engineerDeadline;
    private Date reviewerDeadline;

    public Requirement(Long id, String name, String description, Integer stage, Long creatorId, Long engineerId, Long reviewerId, Integer priority, Date engineerDeadline, Date reviewerDeadline) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.stage = stage;
        this.creatorId = creatorId;
        this.engineerId = engineerId;
        this.reviewerId = reviewerId;
        this.priority = priority;
        this.engineerDeadline = engineerDeadline;
        this.reviewerDeadline = reviewerDeadline;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStage() {
        return stage;
    }

    public void setStage(Integer stage) {
        this.stage = stage;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Long getEngineerId() {
        return engineerId;
    }

    public void setEngineerId(Long engineerId) {
        this.engineerId = engineerId;
    }

    public Long getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(Long reviewerId) {
        this.reviewerId = reviewerId;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Date getEngineerDeadline() {
        return engineerDeadline;
    }

    public void setEngineerDeadline(Date engineerDeadline) {
        this.engineerDeadline = engineerDeadline;
    }

    public Date getReviewerDeadline() {
        return reviewerDeadline;
    }

    public void setReviewerDeadline(Date reviewerDeadline) {
        this.reviewerDeadline = reviewerDeadline;
    }
}