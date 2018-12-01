package servlet;

import com.google.gson.Gson;
import database.RequirementDAO;
import model.requirement.Requirement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/GetReqById")
public class GetReqById extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequirementDAO requirementDAO = new RequirementDAO();
        Long id = Long.parseLong(request.getParameter("id"));

        Requirement req = requirementDAO.getRequirement(id);
        Gson gson = new Gson();
        String data = gson.toJson(req);
        response.setContentType("json/application");
        PrintWriter out = response.getWriter();
        out.write(data);
	}
	
}
