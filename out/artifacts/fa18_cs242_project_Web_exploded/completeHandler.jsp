<%@ page import="java.util.Date" %>
<%@ page import="model.completedReq.CompletedRequirement" %>
<%@ page import="database.CompletedRequirementDAO" %>
<%
    Long requirementId = Long.parseLong(request.getParameter("requirement-id"));
    Long engineerId = Long.parseLong(request.getParameter("engineer-id"));
    String comment = request.getParameter("complete-comment");
    Date completedDate = new Date();
    java.sql.Date sqlDate = new java.sql.Date(completedDate.getTime());
    CompletedRequirement completedRequirement = new CompletedRequirement(requirementId,engineerId,comment,sqlDate);
    CompletedRequirementDAO completedRequirementDAO = new CompletedRequirementDAO();
    completedRequirementDAO.insert(completedRequirement);
    response.sendRedirect("viewReq.jsp");
%>