package test;

import com.gargoylesoftware.htmlunit.html.*;
import com.gargoylesoftware.htmlunit.javascript.host.html.HTMLSelectElement;
import database.RequirementDAO;
import junit.framework.Assert;
import com.gargoylesoftware.htmlunit.WebClient;

import database.UserDAO;
import model.requirement.Requirement;
import org.junit.jupiter.api.Test;


public class testCreate {

    static final String ADDRESS = "http://localhost:8080/fa18-cs242-project/";

    @Test
    public void testCreatePage() throws Exception {
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
            Assert.assertTrue(afterDropDown.asXml().contains("Create a Requirement"));
            HtmlAnchor create= afterDropDown.getAnchorByText("Create a Requirement");
            HtmlPage createClick = create.click();
            Assert.assertTrue(createClick.asXml().contains("Save changes"));

        }
    }
    @Test
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
            reviewer.type("usernumber3");
            HtmlButton createSubmit = (HtmlButton)createClick.getElementById("create-submit");
            HtmlPage afterCreate = createSubmit.click();
            Assert.assertTrue(afterCreate.asXml().contains("test req"));
            RequirementDAO requirementDAO = new RequirementDAO();
            boolean delete = requirementDAO.deleteReq("test req");
            Assert.assertTrue(delete);
        }
    }

    @Test
    public void testNotExistRole() throws Exception {
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
            HtmlButton submit = (HtmlButton) page.getElementById("login-submit");
            HtmlPage afterSubmit = submit.click();
            HtmlAnchor dropDown = afterSubmit.getAnchorByText("Requirement");
            HtmlPage afterDropDown = dropDown.click();
            HtmlAnchor create = afterDropDown.getAnchorByText("Create a Requirement");
            HtmlPage createClick = create.click();
            HtmlForm createForm = createClick.getFormByName("create-req");
            HtmlTextInput name = createForm.getInputByName("name");
            name.click();
            name.type("test req");
            HtmlSelect select = createForm.getSelectByName("priority");
            HtmlOption option = select.getOptionByValue("1");
            select.setSelectedAttribute(option, true);
            HtmlTextInput engineer = createForm.getInputByName("engineer");
            engineer.click();
            engineer.type("not exist");
            HtmlTextInput reviewer = createForm.getInputByName("reviewer");
            HtmlPage notExist = reviewer.click();
            Assert.assertTrue(notExist.asXml().contains("No such person."));
        }
    }
}
