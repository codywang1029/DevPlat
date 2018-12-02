package servlet;

import com.google.gson.Gson;
import database.CompletedRequirementDAO;
import database.RequirementDAO;
import model.completedReq.CompletedRequirement;
import model.requirement.Requirement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/GetCompletedReq")
public class GetCompletedReq extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CompletedRequirementDAO completedRequirementDAO = new CompletedRequirementDAO();
        Long id = Long.parseLong(request.getParameter("id"));

        CompletedRequirement req = completedRequirementDAO.get(id);
        Gson gson = new Gson();
        String data = gson.toJson(req);
        response.setContentType("json/application");
        PrintWriter out = response.getWriter();
        out.write(data);
	}
	
}
