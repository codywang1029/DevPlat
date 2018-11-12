<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<%@ page import="database.UserDAO"%>
<%@ page import="model.user.User"%>

<!DOCTYPE html>
<html>
<head>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<link href="css/login.css" type="text/css" rel="stylesheet"
	id="login-css">
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</head>
<body>
	<div class='container center-block' style="width: 50%">
		<div class='login-panel'>
			<div class='heading'>
				<div class="row">
					<div class="col">
						<a class="active" id="login-btn">Login</a>
					</div>
					<div class="col">
						<a id="register-btn">Register</a>
					</div>
				</div>
			</div>
			<div id='login'>
				<form role='form' id='login-form' method="post" action="login.jsp">
					<div class='form-group'>
						<label>Username</label> <input type='text' class='form-control'
							required name='login-username' placeholder='Enter username'>
					</div>
					<div class='form-group'>
						<label>Password</label> <input type='password'
							class='form-control' required name='login-password'
							placeholder='Password'>
						<%
							String username = request.getParameter("login-username");
							String password = request.getParameter("login-password");
							String newUsername = request.getParameter("register-username");
							String newPassword = request.getParameter("register-password");
							UserDAO userDAO = new UserDAO();
							if (username != null && password != null) {
								User user = new User(null, username, password);
								User loginResult = userDAO.login(user);
								if (loginResult!=null){
									//login successful
									String redirectURL = "http://localhost:8080/fa18-cs242-project/main.jsp";
									session.setAttribute("user", loginResult);
								    response.sendRedirect(redirectURL);
								}
								else{
									//incorrect password
									%>
						<span id="login-fail" style="color: red">Incorrect username
							and password.</span>
						<%
								}
							} 
							else if (newUsername != null && newPassword != null) {
								User user = new User(null, newUsername, newPassword);
								boolean registerResult = userDAO.register(user);
								if (registerResult) {
						%>
						<span style="color: green">Successfully registered</span>
						<%
							} else {
						%>
						<span style="color: red">Registration Failed</span>
						<%
							}
							}
						%>
					</div>
					<div class='text-center'>
						<button id='login-submit' class='center-block btn'>Log in</button>
					</div>
				</form>
			</div>
			<div id='register' style='display: none'>
				<form role='form' id='register-form'
					oninput='register-cpassword.setCustomValidity(register-password.value != register-cpassword.value ? "Password does not match." : "")'
					method="post" action="login.jsp">
					<div class='form-group'>
						<label>Username</label> <input type='text' class='form-control'
							required name='register-username' placeholder='Enter username'>
						<span id="name-exist" style="color: red; display: none">Username
							already exists.</span> <span id="name-ok"
							style="color: green; display: none">Username is valid.</span>
					</div>
					<div class='form-group'>
						<label>Password</label> <input type='password'
							class='form-control' required name='register-password'
							placeholder='Password'>
					</div>
					<div class='form-group'>
						<label>Confirm Password</label> <input type='password'
							class='form-control' required name='register-cpassword'
							placeholder='Re-enter Password'>
					</div>
					<div class='text-center'>
						<button type='submit' id='register-submit'
							class='center-block btn'>Register</button>
					</div>
				</form>
			</div>

		</div>

	</div>
</body>
<script type="text/javascript" src='js/login.js'></script>
</html>
