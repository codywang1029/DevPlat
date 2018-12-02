package test;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import database.RequirementDAO;
import junit.framework.Assert;
import org.junit.jupiter.api.*;


public class testView {

    static final String ADDRESS = "http://localhost:8080/fa18-cs242-project/";

    @Test
    @BeforeEach
    public void testSuccessCreate() throws Exception {
        try (final WebClient webClient = new WebClient()) {
            HtmlPage page = webClient.getPage(ADDRESS);
            webClient.getOptions().setJavaScriptEnabled(true);
            webClient.getOptions().setThrowExceptionOnScriptError(false);
            HtmlForm form = page.getFormByName("login-form");
            HtmlTextInput username = form.getInputByName("login-username");
            HtmlPasswordInput pass = form.getInputByName("login-password");
            username.click();
            username.type("usernumber1");
            pass.click();
            pass.type("123456");
            HtmlButton submit = (HtmlButton)page.getElementById("login-submit");
            HtmlPage afterSubmit = submit.click();
            HtmlAnchor dropDown = afterSubmit.getAnchorByText("Requirement");
            HtmlPage afterDropDown = dropDown.click();
            HtmlAnchor create= afterDropDown.getAnchorByText("Create a Requirement");
            HtmlPage createClick = create.click();
            HtmlForm createForm = createClick.getFormByName("create-req");
            HtmlTextInput name = createForm.getInputByName("name");
            name.click();
            name.type("test req");
            HtmlSelect select =  createForm.getSelectByName("priority");
            HtmlOption option = select.getOptionByValue("1");
            select.setSelectedAttribute(option, true);
            HtmlTextInput engineer = createForm.getInputByName("engineer");
            engineer.click();
            engineer.type("usernumber2");
            HtmlTextInput reviewer = createForm.getInputByName("reviewer");
            reviewer.click();
            reviewer.type("usernumber4");
            HtmlButton createSubmit = (HtmlButton)createClick.getElementById("create-submit");
            HtmlPage afterCreate = createSubmit.click();
            Assert.assertTrue(afterCreate.asXml().contains("test req"));
        }
    }
    @Test
    public void testReqInEngineer() throws Exception {
        try (final WebClient webClient = new WebClient()) {
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
            HtmlButton submit = (HtmlButton) page.getElementById("login-submit");
            HtmlPage afterSubmit = submit.click();
            HtmlAnchor dropDown = afterSubmit.getAnchorByText("Requirement");
            HtmlPage afterDropDown = dropDown.click();
            HtmlAnchor view= afterDropDown.getAnchorByText("My Requirement");
            HtmlPage viewReq = view.click();
            Assert.assertTrue(viewReq.asXml().contains("test req"));
            Assert.assertTrue(viewReq.asXml().contains("fas fa-wrench"));
        }
    }

    @Test
    @AfterEach
    public void testReqNotInReviewer() throws Exception {
        try (final WebClient webClient = new WebClient()) {
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
            Assert.assertTrue(!viewReq.asXml().contains("test req"));
            RequirementDAO requirementDAO = new RequirementDAO();
            requirementDAO.deleteReq("test req");
        }
    }
}
