package model.reviewedReq;

import java.sql.Date;

public class ReviewedReq {
    private Long requirementId;
    private Long reviewedId;
    private String comment;
    private Date reviewedDate;


    public ReviewedReq(Long requirementId, Long reviewedId, String comment, Date reviewedDate) {
        this.requirementId = requirementId;
        this.reviewedId = reviewedId;
        this.comment = comment;
        this.reviewedDate = reviewedDate;
    }

    public Long getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(Long requirementId) {
        this.requirementId = requirementId;
    }

    public Long getReviewedId() {
        return reviewedId;
    }

    public void setReviewedId(Long reviewedId) {
        this.reviewedId = reviewedId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getReviewedDate() {
        return reviewedDate;
    }

    public void setReviewedDate(Date reviewedDate) {
        this.reviewedDate = reviewedDate;
    }
}
