<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<%@ page import="model.user.User"%>
	

	
<%
	User user = (User)session.getAttribute("user");
	if (user==null){
		String redirectURL = "http://localhost:8080/fa18-cs242-project";
	    response.sendRedirect(redirectURL);
	}
%>

<!DOCTYPE html>
<html>
<head>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<link href="css/main.css" type="text/css" rel="stylesheet"
	id="main-css">
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<title>DevPlat Home</title>
</head>
<body class="bg-dark">
<%@include file="nav.jsp"%>
<div class="text-center">
    <div class="bg-transparent text-light" style="margin-top:100px;font-size: 50px">Welcome, <%=user.getUsername()%></div>
</div>


<div class="card-group" style="margin: 100px;">
	<div class="card">

		<div class="card-body">
			<h5 class="card-title">Stats</h5>
			<p class="card-text">TODO: Add visualized stat for user</p>

		</div>
	</div>
	<div class="card">

		<div class="card-body">
			<h5 class="card-title">Upcoming</h5>
			<p class="card-text">TODO: Display tasks whose deadline is closed.</p>
		</div>
	</div>
	<div class="card">

		<div class="card-body">
			<h5 class="card-title">Favorite</h5>
			<p class="card-text">TODO: Display tasks that is favorited by the user.</p>
		</div>
	</div>
</div>
</body>
<script type="text/javascript" src='js/main.js'></script>
</html>
