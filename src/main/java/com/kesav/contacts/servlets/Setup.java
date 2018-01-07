package com.kesav.contacts.servlets;

import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.kesav.contacts.entities.Address;
import com.kesav.contacts.entities.Contact;
import com.kesav.contacts.repositories.AddressRepository;
import com.kesav.contacts.repositories.ContactRepository;

@WebListener
public class Setup implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent sce) {

	}

	public void contextInitialized(ServletContextEvent sce) {
		AddressRepository addressRepository = new AddressRepository();
		ContactRepository contactRepository = new ContactRepository();
		try {
			addressRepository.init();
			contactRepository.init();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void contextInitialized1(ServletContextEvent arg0) {

	}

}
