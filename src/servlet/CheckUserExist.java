package servlet;

import database.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/CheckUserExist")
public class CheckUserExist extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		UserDAO dao = new UserDAO();
		try {
			if (dao.existUser(username)) {
				response.setContentType("html/text");
				response.getWriter().write("success");
			}
			else {
				response.setContentType("html/text");
				response.getWriter().write("fail");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
}
