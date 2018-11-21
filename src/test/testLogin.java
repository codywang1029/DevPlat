package test;

import junit.framework.Assert;


import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import org.junit.jupiter.api.Test;

public class testLogin{
	
	static final String ADDRESS = "http://localhost:8080/fa18-cs242-project/";
	
	@Test
	public void testHomePage() throws Exception {
		try (final WebClient webClient = new WebClient()) {
			HtmlPage page = webClient.getPage(ADDRESS);
			Assert.assertEquals("DevPlat", page.getTitleText());
			
			final String pageAsXml = page.asXml();
	        Assert.assertTrue(pageAsXml.contains("<div id=\"login\">"));
		}
	}
	
	@Test
	public void testClickRegister() throws Exception {
		try (final WebClient webClient = new WebClient()) {
			HtmlPage page = webClient.getPage(ADDRESS);
			webClient.getOptions().setJavaScriptEnabled(true); 
			webClient.getOptions().setThrowExceptionOnScriptError(false);
			HtmlAnchor anchor = page.getAnchorByText("Register");
			//click the register tab
			HtmlPage click = anchor.click();
			System.out.println(click.asXml());
			Assert.assertTrue(click.asXml().contains("<div id=\"register\""));	
		}
	}
	
	@Test
	public void testFailLogin() throws Exception {
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
			pass.type("12345678");
			HtmlButton submit = (HtmlButton)page.getElementById("login-submit");
			HtmlPage afterSubmit = submit.click();  
			
			Assert.assertTrue(afterSubmit.asXml().contains("Incorrect username and password."));	
		}
	}
	
	@Test
	public void testSuccessLogin() throws Exception {
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
			Assert.assertTrue(afterSubmit.asXml().contains("hello usernumber1"));	
		}
	}
	
	
	
	
	
	
}
