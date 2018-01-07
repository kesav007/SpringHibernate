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

import com.kesav.contacts.entities.Address;
import com.kesav.contacts.entities.Contact;
import com.kesav.contacts.repositories.AddressRepository;
import com.kesav.contacts.repositories.ContactRepository;

@WebServlet("address")
public class AddressListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		AddressRepository addressRepository = new AddressRepository();
		List<Address> addresses = new ArrayList<Address>();
		try {
			Long id = Long.parseLong(req.getParameter("addressId"));
			addresses.add(addressRepository.find(id));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		req.setAttribute("addresses", addresses);
		RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/address.jsp");
		dispatcher.forward(req, res);
	}

}
