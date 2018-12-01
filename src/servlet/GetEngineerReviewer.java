package servlet;

import com.google.gson.Gson;
import database.RequirementDAO;
import database.UserDAO;
import model.requirement.Requirement;
import model.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/GetEngineerReviewer")
public class GetEngineerReviewer extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO userDAO = new UserDAO();
        Long engineerId = Long.parseLong(request.getParameter("engineerId"));
        Long reviewerId = Long.parseLong(request.getParameter("reviewerId"));

        User engineer = userDAO.getUser(engineerId);
        User reviewer = userDAO.getUser(reviewerId);

        String data = "{\"engineer\":\""+engineer.getUsername()+"\", \"reviewer\":\""+reviewer.getUsername()+"\"}";
        response.setContentType("json/application");
        PrintWriter out = response.getWriter();
        out.write(data);
	}
	
}
