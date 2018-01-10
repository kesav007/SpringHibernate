package com.kesav.contacts.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.kesav.contacts.entities.Contact;
import com.kesav.contacts.repositories.ContactRepository;

@WebServlet("contacts")
public class ContactListServlet extends HttpServlet {
	static final long serialVersionUID = 1L;

	Logger log = Logger.getLogger(ContactListServlet.class);
	private final ContactRepository contactRepository = new ContactRepository();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		List<Contact> contacts = contactRepository.findAll();
		for (Contact contact : contacts) {
			log.debug(contact);
		}
		request.setAttribute("contacts", contacts);
		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/contacts.jsp");
		dispatcher.forward(request, response);
	}

}
