package servlet;

import database.LogDAO;
import database.RequirementDAO;
import model.log.Log;
import model.requirement.Requirement;
import model.user.CurrentUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CloseReq")
public class CloseReq extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequirementDAO requirementDAO = new RequirementDAO();
        Long id = Long.parseLong(request.getParameter("id"));
        Requirement req = requirementDAO.getRequirement(id);
        req.setStage(3);

        boolean success = requirementDAO.updateReq(req);
        if (success){
            CurrentUser currentUser = CurrentUser.getInstance();
            LogDAO logDAO = new LogDAO();
            Log log= new Log(null,currentUser.id,id,"Closed");
            logDAO.insertLog(log);
            response.setContentType("html/text");
            response.getWriter().write("success");
        }
        else{
            response.setContentType("html/text");
            response.getWriter().write("fail");
        }


	}
	
}
