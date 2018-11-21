<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="ISO-8859-1"%>
<%@ page import="model.user.User"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.sql.Date" %>
<%@ page import="database.UserDAO" %>
<%@ page import="model.requirement.Requirement" %>
<%@ page import="database.RequirementDAO" %>

<%
    String name = request.getParameter("name");
    String priorityStr = request.getParameter("priority");
    String engineerName = request.getParameter("engineer");
    String reviewerName = request.getParameter("reviewer");
    String engineerDeadlineStr = request.getParameter("engineer-deadline");
    String reviewerDeadlineStr = request.getParameter("reviewer-deadline");
    String description = request.getParameter("description");
    User currUser = (User)session.getAttribute("user");
    if (name!=null && engineerName!=null && currUser!=null){
        UserDAO userDAO = new UserDAO();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date engineerDeadline = sdf1.parse(engineerDeadlineStr);
        Date sqlEngineerDate = new Date(engineerDeadline.getTime());
        java.util.Date reviewerDeadline = sdf1.parse(reviewerDeadlineStr);
        Date sqlReviewerDate = new Date(reviewerDeadline.getTime());
        Integer priority = Integer.parseInt(priorityStr);
        Long engineerId = userDAO.getId(engineerName);
        Long reviewerId = userDAO.getId(reviewerName);
        Long creatorId = currUser.getId();
        Requirement req = new Requirement(null,name,description,new Integer(0),creatorId,engineerId,reviewerId,priority,sqlEngineerDate,sqlReviewerDate);
        RequirementDAO requirementDAO = new RequirementDAO();
        boolean insert = requirementDAO.insertReq(req);


    }
%>

<!DOCTYPE html>
<html>
<head>
    <link
            href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
            rel="stylesheet" id="bootstrap-css">
    <link href="css/viewReq.css" type="text/css" rel="stylesheet"
          id="main-css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">

    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

    <script
            src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    <title>My Reqs</title>
</head>
<body class="bg-dark">
<%@include file="nav.jsp"%>
<div class="container">
    <div class="row">
        <div class="col-sm-3 bg-light" style="border-radius:8px;margin-top:40px;margin-right:20px;min-height: 500px">

        </div>
        <div class="col-sm-8 bg-light" style="border-radius:8px;margin-top:40px;margin-left:20px;min-height: 500px">
            <div class="heading">
                <i class="fa fa-tasks" aria-hidden="true"></i><span style="margin-left:10px">My Requirements</span>
            </div>
        <hr/>
        </div>
    </div>
</div>

</body>

<script type="text/javascript" src='js/main.js'></script>
</html>
