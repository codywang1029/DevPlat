package test;

import database.RequirementDAO;
import database.ReviewedRequirementDAO;
import junit.framework.Assert;
import model.requirement.Requirement;
import model.reviewedReq.ReviewedReq;
import org.junit.jupiter.api.Test;

import java.sql.Date;

public class testReviewer {

    @Test
    public void testFinishReview(){
        RequirementDAO requirementDAO = new RequirementDAO();
        Requirement req = new Requirement(null,"test reviewer","",new Integer(1),new Long(2),new Long(2),new Long(2),new Integer(1),null,null);
        requirementDAO.insertReq(req);
        req = requirementDAO.getRequirement("test reviewer");
        req.setStage(2);
        requirementDAO.updateReq(req);
        ReviewedRequirementDAO reviewedRequirementDAO =new ReviewedRequirementDAO();
        java.util.Date date = new java.util.Date();
        ReviewedReq reviewedReq = new ReviewedReq(req.getId(),req.getReviewerId(),"comment",new Date(date.getTime()));
        boolean test = reviewedRequirementDAO.insert(reviewedReq);
        Assert.assertTrue(test);
        reviewedReq = reviewedRequirementDAO.get(req.getId());
        Assert.assertEquals(reviewedReq.getComment(),"comment");
        boolean delete = requirementDAO.deleteReq("test reviewer");
        Assert.assertTrue(delete);
    }
}
