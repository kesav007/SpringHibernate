package com.kesav.contacts.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kesav.contacts.entities.Contact;
import com.kesav.contacts.repositories.AddressRepository;
import com.kesav.contacts.repositories.ContactRepository;

@WebServlet("contacts")
public class ContactListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final ContactRepository contactRepository = new ContactRepository();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		List<Contact> contacts = new ArrayList<Contact>();
		try {
			contacts.addAll(contactRepository.findAll());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		req.setAttribute("contacts", contacts);
		RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/contacts.jsp");
		dispatcher.forward(req, res);
	}

}
