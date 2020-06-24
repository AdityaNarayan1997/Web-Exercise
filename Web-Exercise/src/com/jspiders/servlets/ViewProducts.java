package com.jspiders.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspiders.database.ProductDb;
import com.jspiders.entity.Product;

public class ViewProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			PrintWriter out = response.getWriter();
			List<Product> list = ProductDb.viewProducts();
			Iterator<Product> it = list.iterator();
			out.println("<!DOCTYPE html>\r\n" + "<html lang=\"en\">\r\n" + "\r\n" + "<head>\r\n"
					+ "    <meta charset=\"UTF-8\">\r\n"
					+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
					+ "    <link rel=\"stylesheet\" href=\"bootstrap.css\">\r\n" + "    <title>View Books</title>\r\n"
					+ "</head>\r\n" + "\r\n" + "<body>\r\n" + "    <table class=\"table table-striped\">\r\n"
					+ "        <thead>\r\n" + "            <tr>\r\n"
					+ "                <th scope=\"col\">CALL NO</th>\r\n"
					+ "                <th scope=\"col\">Product ID</th>\r\n"
					+ "                <th scope=\"col\">Product Name</th>\r\n"
					+ "                <th scope=\"col\">Product Type</th>\r\n"
					+ "                <th scope=\"col\">Price</th>\r\n"
					+ "                <th scope=\"col\">Quantity</th>\r\n" + "            </tr>\r\n"
					+ "        </thead>");
			while (it.hasNext()) {
				Product prod = it.next();

				out.println(" <tbody>\r\n" + "            <tr>\r\n" + "                <th scope=\"row\">"
						+ prod.getId() + "</th>\r\n" + "                <td>" + prod.getName() + "</td>\r\n"
						+ "                <td>" + prod.getType() + "</td>\r\n" + "                <td>"
						+ prod.getPrice() + "</td>\r\n" + "                <td>" + prod.getQuantity() + "</td>\r\n"
						+ "            </tr>\r\n" + "        </tbody>");
			}

			out.println("</table>\r\n" + "</body>\r\n" + "\r\n" + "</html>");
		} else {
			response.getWriter().println("<h1>Please login first</h1>");
			request.getRequestDispatcher("login.html").include(request, response);
		}

	}
}
