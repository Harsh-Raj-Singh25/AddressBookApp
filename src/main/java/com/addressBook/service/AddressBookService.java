package com.addressBook.service;

import com.addressBook.model.Contact;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class AddressBookService {
	// List to store multiple contacts in memory
	private List<Contact> contactList = new ArrayList<>();

	// Method to add a new contact to the list
	public String addContact(Contact contact) {
		contactList.add(contact);
		return "Contact added successfully!";
	}

	// Method to get all contacts
	public List<Contact> getAllContacts() {
		return contactList;
	}
}