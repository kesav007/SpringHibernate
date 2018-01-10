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

import com.kesav.contacts.entities.Address;
import com.kesav.contacts.repositories.AddressRepository;

@WebServlet("address")
public class AddressListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	Logger log = Logger.getLogger(AddressListServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		AddressRepository addressRepository = new AddressRepository();
		List<Address> addresses = new ArrayList<Address>();
		Long id = Long.parseLong(req.getParameter("addressId"));
		addresses.add(addressRepository.find(id));
		req.setAttribute("addresses", addresses);
		RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/address.jsp");
		dispatcher.forward(req, res);
	}

}
