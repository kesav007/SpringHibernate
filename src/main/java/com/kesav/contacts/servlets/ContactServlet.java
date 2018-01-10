package com.kesav.contacts.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.kesav.contacts.entities.Address;
import com.kesav.contacts.entities.Contact;
import com.kesav.contacts.entities.State;
import com.kesav.contacts.repositories.AddressRepository;
import com.kesav.contacts.repositories.ContactRepository;

@WebServlet("contact")
public class ContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Logger log = Logger.getLogger(ContactServlet.class);
	private final ContactRepository contactRepo = new ContactRepository();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String street = request.getParameter("street");
		String city = request.getParameter("city");
		String stateParam = request.getParameter("state");
		String zip = request.getParameter("zip");
		String name = request.getParameter("name");
		String contactId = request.getParameter("contactId");
		String updateType = request.getParameter("updateType");
		Contact contact = null;
		Address address = null;
		State state = null;
		log.debug("Street: " + street + ", City: " + city + ", State: " + stateParam + ", Zip" + zip + ", Name: " + name + ", ContactId: " + contactId + ", Update Type: " + updateType);
		if (StringUtils.isNotBlank(contactId)) {
			log.debug("Inside Edit");
			contact = contactRepo.find(Long.valueOf(contactId));
			address = contact.getAddress();
			state = address.getState();
			if (StringUtils.isNotBlank(updateType) && updateType.equalsIgnoreCase("update")) {
				log.debug("Inside Update ");
				address.setStreet(street);
				address.setCity(city);
				state.setState(stateParam);
				address.setState(state);
				address.setZip(zip);
				contact.setName(name);
				contact = contactRepo.save(contact);
			} else if (StringUtils.isNotBlank(updateType) && updateType.equalsIgnoreCase("delete")) {
				log.debug("Inside Delete");
				contactRepo.delete(contact);
			}
		} else if (StringUtils.isNotBlank(name) && StringUtils.isNotBlank(street) && StringUtils.isNotBlank(city)
				&& StringUtils.isNotBlank(stateParam) && StringUtils.isNotBlank(zip)) {
			log.debug("Inside Create ");
			state = new State(stateParam);
			address = new Address(street, city, state, zip);
			contact = new Contact(name, address);
			contact = contactRepo.save(contact);
		}
		log.debug("Outside Create ");
		response.sendRedirect("contacts");

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			String view = request.getParameter("view");
			Long id = null;
			Contact contact = new Contact();
			if (StringUtils.isNotBlank(view)) {
				id = Long.valueOf(view);
				contact = contactRepo.find(id);
			}
			request.setAttribute("contact", contact);
			log.debug(contact.toString());
			request.getRequestDispatcher("jsp/viewContact.jsp").forward(request, response);
	}
}
