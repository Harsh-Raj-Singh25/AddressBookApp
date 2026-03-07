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

	// method to edit contact
	public String editContact(String firstName, String lastName, Contact updatedData) {
		for (Contact contact : contactList) {
			// We use the equals logic we wrote in UC1!
			if (contact.getFirstName().equalsIgnoreCase(firstName)
					&& contact.getLastName().equalsIgnoreCase(lastName)) {

				// Updating the fields
				contact.setAddress(updatedData.getAddress());
				contact.setCity(updatedData.getCity());
				contact.setState(updatedData.getState());
				contact.setZip(updatedData.getZip());
				contact.setPhoneNumber(updatedData.getPhoneNumber());
				contact.setEmail(updatedData.getEmail());

				return "Contact updated successfully!";
			}
		}
		return "Contact not found.";
	}

	// Delete contact method

	public String deleteContact(String firstName, String lastName) {
		// removeIf returns true if it actually found and removed something
		boolean isRemoved = contactList.removeIf(contact -> contact.getFirstName().equalsIgnoreCase(firstName)
				&& contact.getLastName().equalsIgnoreCase(lastName));

		if (isRemoved) {
			return "Contact deleted successfully!";
		} else {
			return "Contact not found.";
		}
	}

//  method to ADD multiple contacts
	public String addMultipleContacts(List<Contact> contacts) {
		for (Contact contact : contacts) {
			contactList.add(contact);
		}
		return contacts.size() + " contacts added successfully!";
	}
}