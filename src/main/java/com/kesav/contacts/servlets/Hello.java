package com.kesav.contacts.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello")
public class Hello extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		/*		
		 * res.setContentType("text/html");
		 * res.getWriter().println("<html><head><title>hello1</title></head><body>Hello World !</body></html>");
		*/
		String name = req.getParameter("name");
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("jsp/hello.jsp");
		req.setAttribute("name", name);
		requestDispatcher.forward(req, res);
	}

}
