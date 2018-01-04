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
public class Setup implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

	public void contextInitialized2(ServletContextEvent sce) {
		AddressRepository addressRepository = new AddressRepository();
		ContactRepository contactRepository = new ContactRepository();
		try {
			addressRepository.init();
			contactRepository.init();
			Address address = new Address("21398 Fultonham Cir", "Ashburn", "VA", "20147");
			Address address2 = new Address("11571 ABC", "Reston", "VA", "20171");
			Address address3 = new Address("2345 bcc", "Sterling", "VA", "20166");
			addressRepository.create(address);
			addressRepository.create(address2);
			addressRepository.create(address3);
			System.out.println(addressRepository.findAll());

			Contact contact = new Contact("Kesav", address.getId());
			Contact contact2 = new Contact("Nallan", address2.getId());
			contactRepository.create(contact);
			contactRepository.create(contact2);
			System.out.println(contactRepository.findAll());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
	}

}
