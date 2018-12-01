<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="ISO-8859-1"%>
<%@ page import="model.user.User"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.sql.Date" %>
<%@ page import="database.UserDAO" %>
<%@ page import="model.requirement.Requirement" %>
<%@ page import="database.RequirementDAO" %>
<%@ page import="java.util.List" %>

<%
    User user = (User)session.getAttribute("user");
    if (user==null){
        String redirectURL = "http://localhost:8080/fa18-cs242-project";
        response.sendRedirect(redirectURL);
    }
%>

<%
    String name = request.getParameter("name");
    String priorityStr = request.getParameter("priority");
    String engineerName = request.getParameter("engineer");
    String reviewerName = request.getParameter("reviewer");
    String engineerDeadlineStr = request.getParameter("engineer-deadline");
    String reviewerDeadlineStr = request.getParameter("reviewer-deadline");
    String description = request.getParameter("description");
    User currUser = (User)session.getAttribute("user");
    RequirementDAO requirementDAO = new RequirementDAO();
    if (name!=null && engineerName!=null && currUser!=null){
        UserDAO userDAO = new UserDAO();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        Date sqlEngineerDate=null;
        Date sqlReviewerDate=null;
        if (engineerDeadlineStr!=""){
            java.util.Date engineerDeadline = sdf1.parse(engineerDeadlineStr);
            sqlEngineerDate = new Date(engineerDeadline.getTime());
        }

        if (reviewerDeadlineStr!=""){
            java.util.Date reviewerDeadline = sdf1.parse(reviewerDeadlineStr);
            sqlReviewerDate = new Date(reviewerDeadline.getTime());
        }
        Integer priority = Integer.parseInt(priorityStr);
        Long engineerId = userDAO.getId(engineerName);
        Long reviewerId = userDAO.getId(reviewerName);
        Long creatorId = currUser.getId();
        Requirement req = new Requirement(null,name,description,new Integer(0),creatorId,engineerId,reviewerId,priority,sqlEngineerDate,sqlReviewerDate);
        boolean insert = requirementDAO.insertReq(req);
    }
    else{
        name = request.getParameter("edit-name");
        if (name!=null && currUser!=null){
            Long id = Long.parseLong(request.getParameter("edit-id"));
            priorityStr = request.getParameter("edit-priority");
            engineerName = request.getParameter("edit-engineer");
            reviewerName = request.getParameter("edit-reviewer");
            engineerDeadlineStr = request.getParameter("edit-engineer-deadline");
            reviewerDeadlineStr = request.getParameter("edit-reviewer-deadline");
            description = request.getParameter("edit-description");
            Integer stage = Integer.parseInt(request.getParameter("edit-stage"));
            UserDAO userDAO = new UserDAO();
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            Date sqlEngineerDate=null;
            Date sqlReviewerDate=null;
            if (engineerDeadlineStr!=""){
                java.util.Date engineerDeadline = sdf1.parse(engineerDeadlineStr);
                sqlEngineerDate = new Date(engineerDeadline.getTime());
            }

            if (reviewerDeadlineStr!=""){
                java.util.Date reviewerDeadline = sdf1.parse(reviewerDeadlineStr);
                sqlReviewerDate = new Date(reviewerDeadline.getTime());
            }
            Integer priority = Integer.parseInt(priorityStr);
            Long engineerId = userDAO.getId(engineerName);
            Long reviewerId = userDAO.getId(reviewerName);
            Long creatorId = currUser.getId();
            Requirement req = new Requirement(id,name,description,stage,creatorId,engineerId,reviewerId,priority,sqlEngineerDate,sqlReviewerDate);
            boolean update = requirementDAO.updateReq(req);
        }

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
                <i class="fas fa-project-diagram"></i><span style="margin-left:10px">My Requirements</span>
            </div>
        <hr/>


            <%
                List<Requirement> reqs = requirementDAO.getReqAsCreator(currUser.getId());
                for (Requirement req:reqs){
                    %>

            <div class="req-item" id=<%=req.getId()%>>
                <div class = "req-icon">
                    <i class="fas fa-pencil-ruler"></i>
                </div>
                <div class = "req-name">
                    <%=req.getName()%>
                </div>
                <div class="req-toolbar" id=<%=req.getId()%>>
                    <div class="req-button" name="edit-req" id="<%=req.getId()%>">
                        <i class="fas fa-edit"></i>
                    </div>
                    <div class="req-button" name="delete-req" id=<%=req.getId()%>>
                        <i class="fas fa-trash"></i>
                    </div>
                </div>
            </div>
            <%
                }
            %>



            <%
                reqs = requirementDAO.getReqAsEngineer(currUser.getId());
                for (Requirement req:reqs){
            %>
            <div class="req-item" id=<%=req.getId()%>>
                <div class = "req-icon">
                    <i class="fas fa-wrench"></i>
                </div>
                <div class = "req-name">
                    <%=req.getName()%>
                </div>
                <div class="req-toolbar" id=<%=req.getId()%>>
                    <div class="req-button" name="engineer-complete" id="<%=req.getId()%>">
                        <i class="fas fa-check"></i>
                    </div>
                </div>
            </div>
            <%
                }
            %>


            <%
                reqs = requirementDAO.getReqAsReviwer(currUser.getId());
                for (Requirement req:reqs){
            %>
            <div class="req-item" id=<%=req.getId()%>>
                <div class = "req-icon">
                    <i class="fas fa-search"></i>
                </div>
                <div class = "req-name">
                    <%=req.getName()%>
                </div>
                <div class="req-toolbar" id=<%=req.getId()%>>
                    <div class="req-button" name="reviewer-complete" id="<%=req.getId()%>">
                        <i class="fas fa-user-check"></i>
                    </div>
                </div>
            </div>
            <%
                }
            %>
        </div>
    </div>
</div>
<%@include file="editReq.jsp"%>
<%@include file="completeReq.jsp"%>
</body>
<script type="text/javascript" src='js/main.js'></script>
<script type="text/javascript" src="js/viewReq.js"></script>
</html>
