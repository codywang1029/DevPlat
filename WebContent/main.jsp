<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<%@ page import="model.user.User"%>
	

	
<%
	User user = (User)session.getAttribute("user");
	if (user==null){
		String redirectURL = "http://localhost:8080/fa18-cs242-project";
	    response.sendRedirect(redirectURL);
	}
	else{
		%>
		hello <%=user.getUsername() %>
		<%
	}
%>

