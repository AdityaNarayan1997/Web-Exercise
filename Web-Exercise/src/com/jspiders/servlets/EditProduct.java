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

public class EditProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			String pid = request.getParameter("id");
			String name = request.getParameter("name");
			String type = request.getParameter("type");
			String pri = request.getParameter("price");
			String quan = request.getParameter("quan");
			int id = Integer.parseInt(pid);
			int price = Integer.parseInt(pri);
			int quantity = Integer.parseInt(quan);
			if (ProductDb.checkAvailability(id) == true) {
				int status = ProductDb.editProduct(new Product(id, name, type, price, quantity));

				if (status != 0) {
					out.println("<!DOCTYPE html>\r\n" + "<html lang=\"en\">\r\n" + "<head>\r\n"
							+ "    <meta charset=\"UTF-8\">\r\n"
							+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
							+ "</head>\r\n" + "<body>\r\n"
							+ "<h1 style=\"color:rgba(26, 25, 25, 0.863);\">Product Updated to Database</h1>\r\n"
							+ "</body>\r\n" + "</html>");

					request.getRequestDispatcher("editpage.html").include(request, response);

				}
			} else {
				out.println("Product Not Found in Database");
				out.println("<a href=\"homepage.html\">GoTo HomePage</a>");
			}
		} else {
			response.getWriter().println("<h1>Please login first</h1>");
			request.getRequestDispatcher("login.html").include(request, response);
		}

	}

}
