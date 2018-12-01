package servlet;

import database.RequirementDAO;
import database.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/DeleteReq")
public class DeleteReq extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequirementDAO requirementDAO = new RequirementDAO();
        Long id = Long.parseLong(request.getParameter("id"));
        boolean success = requirementDAO.deleteReq(id);
        if (success){
            response.setContentType("html/text");
            response.getWriter().write("success");
        }
        else{
            response.setContentType("html/text");
            response.getWriter().write("fail");
        }


	}
	
}
