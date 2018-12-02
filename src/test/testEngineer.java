package test;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import database.RequirementDAO;
import junit.framework.Assert;
import model.requirement.Requirement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class testEngineer {

    static final String ADDRESS = "http://localhost:8080/fa18-cs242-project/";

    @Test
    public void testStageOneEngineer() throws Exception {
        try (final WebClient webClient = new WebClient()) {
            RequirementDAO requirementDAO = new RequirementDAO();
            Requirement test = new Requirement(null,"test req","",new Integer(1),new Long(2),new Long(3),new Long(6),1,null,null);
            requirementDAO.insertReq(test);
            HtmlPage page = webClient.getPage(ADDRESS);
            webClient.getOptions().setJavaScriptEnabled(true);
            webClient.getOptions().setThrowExceptionOnScriptError(false);
            HtmlForm form = page.getFormByName("login-form");
            HtmlTextInput username = form.getInputByName("login-username");
            HtmlPasswordInput pass = form.getInputByName("login-password");
            username.click();
            username.type("usernumber2");
            pass.click();
            pass.type("123456");
            HtmlButton submit = (HtmlButton)page.getElementById("login-submit");
            HtmlPage afterSubmit = submit.click();
            HtmlAnchor dropDown = afterSubmit.getAnchorByText("Requirement");
            HtmlPage afterDropDown = dropDown.click();
            HtmlAnchor create= afterDropDown.getAnchorByText("My Requirement");
            HtmlPage createClick = create.click();
            Assert.assertTrue(!createClick.asXml().contains("test req"));
            requirementDAO.deleteReq("test req");
        }
    }

    @Test
    public void testStageOneReviewer() throws Exception {
        try (final WebClient webClient = new WebClient()) {
            RequirementDAO requirementDAO = new RequirementDAO();
            Requirement test = new Requirement(null,"test req","",new Integer(1),new Long(2),new Long(3),new Long(6),1,null,null);
            requirementDAO.insertReq(test);
            HtmlPage page = webClient.getPage(ADDRESS);
            webClient.getOptions().setJavaScriptEnabled(true);
            webClient.getOptions().setThrowExceptionOnScriptError(false);
            HtmlForm form = page.getFormByName("login-form");
            HtmlTextInput username = form.getInputByName("login-username");
            HtmlPasswordInput pass = form.getInputByName("login-password");
            username.click();
            username.type("usernumber4");
            pass.click();
            pass.type("123456");
            HtmlButton submit = (HtmlButton) page.getElementById("login-submit");
            HtmlPage afterSubmit = submit.click();
            HtmlAnchor dropDown = afterSubmit.getAnchorByText("Requirement");
            HtmlPage afterDropDown = dropDown.click();
            HtmlAnchor view= afterDropDown.getAnchorByText("My Requirement");
            HtmlPage viewReq = view.click();
            Assert.assertTrue(viewReq.asXml().contains("test req"));
            Assert.assertTrue(viewReq.asXml().contains("fas fa-search"));
            requirementDAO.deleteReq("test req");
        }
    }


}
