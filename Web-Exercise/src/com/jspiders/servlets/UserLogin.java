package com.jspiders.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jspiders.database.UserDb;

public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		String email = request.getParameter("uname");
		String password = request.getParameter("psw");

		boolean bool = UserDb.userAuthentication(email, password);
		if (bool == true) {
			request.getRequestDispatcher("homepage.html").include(request, response);
		} else {
			response.getWriter().println("<!DOCTYPE html>\r\n" + "<html lang=\"en\">\r\n" + "\r\n" + "<head>\r\n"
					+ "    <meta charset=\"UTF-8\">\r\n"
					+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
					+ "    <title>Error</title>\r\n" + "    <link rel=\"stylesheet\" href=\"bootstrap.css\">\r\n"
					+ "    <style>\r\n" + "        h1 {\r\n" + "            color: bisque;\r\n" + "        }\r\n"
					+ "    </style>\r\n" + "</head>\r\n" + "\r\n" + "<body>\r\n"
					+ "    <h1>Invalid Credentials</h1>\r\n" + "</body>\r\n" + "\r\n" + "</html>");
			request.getRequestDispatcher("login.html").include(request, response);
		}
	}

}
