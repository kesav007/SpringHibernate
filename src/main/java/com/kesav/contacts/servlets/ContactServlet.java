package com.kesav.contacts.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.kesav.contacts.entities.Address;
import com.kesav.contacts.entities.Contact;
import com.kesav.contacts.repositories.AddressRepository;
import com.kesav.contacts.repositories.ContactRepository;

@WebServlet("contact")
public class ContactServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final ContactRepository contactRepo = new ContactRepository();
	private final AddressRepository addressRepo = new AddressRepository();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String street = request.getParameter("street");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zip = request.getParameter("zip");
		String name = request.getParameter("name");
		String contactId= request.getParameter("contactId");
		String updateType = request.getParameter("updateType");
		Contact contact = null;
		Address address = null;
		System.out.println("***********************************************************");
		System.out.println(updateType);
		System.out.println("***********************************************************");
		try {
			if(StringUtils.isNotBlank(contactId)){
				contact = contactRepo.find(Long.valueOf(contactId));
				address = addressRepo.find(contact.getAddressId());
				if(StringUtils.isNotBlank(updateType) && updateType.equalsIgnoreCase("update")) {
					address.setStreet(street);
					address.setCity(city);
					address.setState(state);
					address.setZip(zip);
					contact.setName(name);
					addressRepo.update(address);
					contactRepo.update(contact);	
				}
				else if(StringUtils.isNotBlank(updateType) && updateType.equalsIgnoreCase("delete")) {
					contactRepo.delete(contact);
					addressRepo.delete(address);					
				}
			}
			else if(StringUtils.isNotBlank(name) && 
					StringUtils.isNotBlank(street) &&
					StringUtils.isNotBlank(city) &&
					StringUtils.isNotBlank(state) &&
					StringUtils.isNotBlank(zip) ) {
				address = new Address(street, city, state, zip);
				addressRepo.create(address);
				contact = new Contact(name, address.getId());
				contactRepo.create(contact);		
			}
			response.sendRedirect("contacts");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
				String view = request.getParameter("view");
				Long id = null;
				Contact contact = new Contact();
				Address address = new Address();
				if(StringUtils.isNotBlank(view)) {
					id = Long.valueOf(request.getParameter("view"));
					contact = contactRepo.find(id);
					address = addressRepo.find(id);			
				}
				request.setAttribute("contact", contact);
				request.setAttribute("address", address);
				System.out.println(address);
				System.out.println(contact);
				request.getRequestDispatcher("jsp/viewContact.jsp").forward(request, response);
		} catch (SQLException e) {
			throw new ServletException(e);
		}

	}
}
