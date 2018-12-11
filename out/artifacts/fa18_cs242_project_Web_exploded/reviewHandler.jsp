<%@ page import="java.util.Date" %>
<%@ page import="model.reviewedReq.ReviewedReq" %>
<%@ page import="database.ReviewedRequirementDAO" %>
<%
    Long requirementId = Long.parseLong(request.getParameter("requirement-id"));
    Long reviewerId = Long.parseLong(request.getParameter("reviewer-id"));
    String comment = request.getParameter("review-comment");
    Date completedDate = new Date();
    java.sql.Date sqlDate = new java.sql.Date(completedDate.getTime());
    ReviewedReq reviewedReq = new ReviewedReq(requirementId,reviewerId,comment,sqlDate);
    ReviewedRequirementDAO reviewedRequirementDAO = new ReviewedRequirementDAO();
    reviewedRequirementDAO.insert(reviewedReq);
    response.sendRedirect("viewReq.jsp");
%>