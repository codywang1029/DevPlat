package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.UserDAO;

@WebServlet("/CheckUsername")
public class CheckUsername extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		UserDAO dao = new UserDAO();
		try {
			if (dao.existUser(username)) {
				response.setContentType("html/text");
				response.getWriter().write("fail");
			}
			else {
				response.setContentType("html/text");
				response.getWriter().write("success");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
}
