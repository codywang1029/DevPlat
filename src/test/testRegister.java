package test;

import java.io.IOException;
import java.net.MalformedURLException;

import junit.framework.Assert;
import org.junit.*;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

import database.UserDAO;
import org.junit.jupiter.api.Test;

public class testRegister {

	static final String ADDRESS = "http://localhost:8080/fa18-cs242-project/";
	
	private HtmlPage clickRegister() throws Exception {
		try (final WebClient webClient = new WebClient()) {
			webClient.getOptions().setJavaScriptEnabled(true); 
			webClient.getOptions().setThrowExceptionOnScriptError(false);
			HtmlPage page = webClient.getPage(ADDRESS);
			HtmlAnchor anchor = page.getAnchorByText("Register");
			HtmlPage click = anchor.click();
			return click;
		}
	}
	
	@Test
	public void testInvalidUsername() throws Exception{
		try (final WebClient webClient = new WebClient()) {
			webClient.getOptions().setJavaScriptEnabled(true); 
			webClient.getOptions().setThrowExceptionOnScriptError(false);
			HtmlPage page1 = webClient.getPage(ADDRESS);
			HtmlAnchor anchor = page1.getAnchorByText("Register");
			HtmlPage page = anchor.click();
			HtmlForm form = page.getFormByName("register-form");
			HtmlTextInput username = form.getInputByName("register-username");  
			HtmlPasswordInput pass = form.getInputByName("registerPassword"); 
			username.click();
			username.type("usernumber1");
			HtmlPage blur = pass.click();
			Assert.assertTrue(blur.asXml().contains("Username already exists."));
			username.click();
			username.type("asdfgh");
			blur = pass.click();
			Assert.assertTrue(blur.asXml().contains("Username is valid."));
		}
	}
	
	@Test
	public void testSuccessRegister() throws Exception{
		try (final WebClient webClient = new WebClient()) {
			webClient.getOptions().setJavaScriptEnabled(true); 
			webClient.getOptions().setThrowExceptionOnScriptError(false);
			HtmlPage page1 = webClient.getPage(ADDRESS);
			HtmlAnchor anchor = page1.getAnchorByText("Register");
			HtmlPage page = anchor.click();
			HtmlForm form = page.getFormByName("register-form");
			HtmlTextInput username = form.getInputByName("register-username");  
			HtmlPasswordInput pass = form.getInputByName("registerPassword"); 
			HtmlPasswordInput cpass = form.getInputByName("registerCpassword"); 
			username.click();
			username.type("usernumber100");
			pass.click();
			pass.type("123456");
			cpass.click();
			cpass.type("123456");
			HtmlButton submit = (HtmlButton)page.getElementById("register-submit");
			HtmlPage afterSubmit = submit.click();  	
			Assert.assertTrue(afterSubmit.asXml().contains("Successfully registered"));
			UserDAO userDAO = new UserDAO();
			int delete = userDAO.delete("usernumber100");
			Assert.assertEquals(1, delete);
			
		}
	}
	
	
}
