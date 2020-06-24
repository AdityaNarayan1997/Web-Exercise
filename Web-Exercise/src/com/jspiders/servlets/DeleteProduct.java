package com.jspiders.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspiders.database.ProductDb;
import com.jspiders.entity.Product;

public class DeleteProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			String pid = request.getParameter("id");
			int id = Integer.parseInt(pid);
			if (ProductDb.checkAvailability(id) == true) {
				int status = ProductDb.deleteProduct(new Product(id));

				if (status != 0) {
					out.println("<!DOCTYPE html>\r\n" + "<html lang=\"en\">\r\n" + "<head>\r\n"
							+ "    <meta charset=\"UTF-8\">\r\n"
							+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
							+ "</head>\r\n" + "<body>\r\n"
							+ "<h1 style=\"color:rgba(26, 25, 25, 0.863);\">Product Deleted from Database</h1>\r\n"
							+ "<a href=\"homepage.html\"><h3>GoTo HomePage</h3></a>"
							+ "</body>\r\n" + "</html>");
					request.getRequestDispatcher("deletepage.html").include(request, response);

				}
			} else {
				out.println("Product Not Found in Database");
				out.println("<a href=\"homepage.html\"><h3>GoTo HomePage</h3></a>");

			}
		} else {
			response.getWriter().println("<h1>Please login first</h1>");
			request.getRequestDispatcher("login.html").include(request, response);
		}

	}

}
