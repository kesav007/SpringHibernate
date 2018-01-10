package com.kesav.contacts.repositories;

import com.kesav.contacts.entities.Contact;

public class ContactRepository extends Repository<Contact> {
	
	public ContactRepository() {
		super(Contact.class);
	}
}