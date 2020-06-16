package com.jspiders.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jspiders.database.UserDb;
import com.jspiders.entity.User;

public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		String email = request.getParameter("uname1");
		String password = request.getParameter("psw1");

		int status = UserDb.addUser(new User(email, password));

		if (status != 0) {
			response.getWriter()
					.println("<!DOCTYPE html>\r\n" + "<html lang=\"en\">\r\n" + "<head>\r\n"
							+ "    <meta charset=\"UTF-8\">\r\n"
							+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
							+ "</head>\r\n" + "<body>\r\n"
							+ "<h1 style=\"color:rgba(26, 25, 25, 0.863);\">SignUp Successfull</h1>\r\n" + "</body>\r\n"
							+ "</html>");
			request.getRequestDispatcher("login.html").include(request, response);
		}
	}
}
