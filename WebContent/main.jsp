<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="ISO-8859-1" %>
<%@ page import="model.user.User" %>
<%@ page import="database.LogDAO" %>
<%@ page import="model.log.Log" %>
<%@ page import="java.util.List" %>
<%@ page import="model.user.CurrentUser" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="database.UserDAO" %>
<%@ page import="model.requirement.Requirement" %>
<%@ page import="database.RequirementDAO" %>


<%
    User user = (User) session.getAttribute("user");
    CurrentUser currentUser = CurrentUser.getInstance();
    currentUser.id = new Long(user.getId().longValue());
    if (user == null) {
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
<%@include file="nav.jsp" %>
<div class="text-center">
    <div class="bg-transparent text-light" style="margin-top:100px;font-size: 50px">Welcome, <%=user.getUsername()%>
    </div>
</div>


<div class="card-group" style="margin: 100px;">
    <div class="card">
        <div class="card-body">
            <h5 class="card-title">Recent</h5>
            <p class="card-text">


                    <%
                LogDAO logDAO = new LogDAO();
                List<Log> logs = logDAO.getLogForUser(user.getId());
                int counter= 0;
                for (Log log : logs){
                    counter++;
                    if (counter==6){
                        break;
                    }
                    Timestamp ts = log.getTime();
                    Date date = new Date();
                    date.setTime(ts.getTime());
                    String formattedDate = new SimpleDateFormat("MM-dd HH:mm").format(date);
                    UserDAO userDAO = new UserDAO();
                    User logUser = userDAO.getUser(log.getUserId());
                    RequirementDAO requirementDAO = new RequirementDAO();
                    Requirement requirement = requirementDAO.getRequirement(log.getRequirementId());
                %>

            <div class="row" id="log-row" style="padding:20px">
                <div class="column">
                    <%=formattedDate%>
                </div>
                <div class="column" style="margin-left: 30px;">
                    <span style="font-weight: bold"><%=logUser.getUsername()+" "%></span><%=log.getAction()%>
                    <%
                        if (requirement.getPriority()==1){
                            %>
                    <span style="color: #009688"><%=" "+requirement.getName()%></span>
                    <%
                        }
                        else if (requirement.getPriority()==2){
                    %>
                    <span style="color: #FFB800"><%=" "+requirement.getName()%></span>
                    <%
                        }
                        else{
                    %>
                    <span style="color: #FF5722"><%=" "+requirement.getName()%></span>
                    <%
                    }
                    %>
                </div>
            </div>
            <%
                }
            %>
            </p>


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
