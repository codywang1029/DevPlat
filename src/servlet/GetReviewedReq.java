package servlet;

import com.google.gson.Gson;
import database.CompletedRequirementDAO;
import database.ReviewedRequirementDAO;
import model.completedReq.CompletedRequirement;
import model.reviewedReq.ReviewedReq;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/GetReviewedReq")
public class GetReviewedReq extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ReviewedRequirementDAO reviewedRequirementDAO = new ReviewedRequirementDAO();
        Long id = Long.parseLong(request.getParameter("id"));

        ReviewedReq req = reviewedRequirementDAO.get(id);
        Gson gson = new Gson();
        String data = gson.toJson(req);
        response.setContentType("json/application");
        PrintWriter out = response.getWriter();
        out.write(data);
	}
	
}
