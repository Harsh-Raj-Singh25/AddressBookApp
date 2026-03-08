package com.addressBook.service;

import com.addressBook.model.AddressBook;
import com.addressBook.model.Contact;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AddressBookService {
	// UC 6: Dictionary of Address Book Name to Address Book Object
	private Map<String, AddressBook> addressBookSystem = new HashMap<>();

	public String createNewAddressBook(String name) {
		if (addressBookSystem.containsKey(name))
			return "Already exists";
		addressBookSystem.put(name, new AddressBook(name));
		return "Created Address Book: " + name;
	}

	public AddressBook getAddressBook(String name) {
		return addressBookSystem.get(name);
	}

	public Set<String> listAllBooks() {
		return addressBookSystem.keySet();
	}

	// UC 8: Search person in a City across all Address Books
	public List<Contact> searchByCity(String city) {
		return addressBookSystem.values().stream() // Get all AddressBook objects
				.flatMap(book -> book.getContactList().stream()) // Flatten all contact lists into one stream
				.filter(contact -> contact.getCity().equalsIgnoreCase(city)) // Filter by city
				.collect(Collectors.toList()); // Collect results
	}

	// UC 8: Search person in a State across all Address Books
	public List<Contact> searchByState(String state) {
		return addressBookSystem.values().stream().flatMap(book -> book.getContactList().stream())
				.filter(contact -> contact.getState().equalsIgnoreCase(state)).collect(Collectors.toList());
	}
}