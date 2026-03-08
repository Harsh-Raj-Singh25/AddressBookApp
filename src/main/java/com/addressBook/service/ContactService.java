package com.addressBook.service;

import com.addressBook.model.AddressBook;
import com.addressBook.model.Contact;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContactService {
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

	// This is the method you were missing or that was named differently
	public void addContacts(AddressBook book, List<Contact> newContacts) {
		if (book != null && newContacts != null) {
			book.getContactList().addAll(newContacts);
		}
	}

	// UC 3: Edit Logic
	public boolean updateContact(AddressBook book, String fName, String lName, Contact newData) {
		if (book == null)
			return false;
		for (Contact c : book.getContactList()) {
			if (c.getFirstName().equalsIgnoreCase(fName) && c.getLastName().equalsIgnoreCase(lName)) {
				c.setAddress(newData.getAddress());
				c.setCity(newData.getCity());
				c.setState(newData.getState());
				c.setZip(newData.getZip());
				c.setPhoneNumber(newData.getPhoneNumber());
				c.setEmail(newData.getEmail());
				return true;
			}
		}
		return false;
	}
	public String addContactSecurely(AddressBook book, Contact newContact) {
        // UC 7: Use Java Streams to check for duplicates by name
        boolean isDuplicate = book.getContactList().stream()
                .anyMatch(existingContact -> existingContact.equals(newContact));

        if (isDuplicate) {
            return "Duplicate Entry! " + newContact.getFirstName() + " already exists in this book.";
        }

        book.getContactList().add(newContact);
        return "Contact added successfully.";
    }
}