package com.addressBook.service;

import com.addressBook.model.AddressBook;
import com.addressBook.model.Contact;

import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
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

	// UC 9: View Persons categorized by City (Returns a Dictionary)
	public Map<String, List<Contact>> viewByCity() {
		return addressBookSystem.values().stream().flatMap(book -> book.getContactList().stream())
				.collect(Collectors.groupingBy(Contact::getCity)); // Groups contacts by their City field
	}

	// UC 9: View Persons categorized by State (Returns a Dictionary)
	public Map<String, List<Contact>> viewByState() {
		return addressBookSystem.values().stream().flatMap(book -> book.getContactList().stream())
				.collect(Collectors.groupingBy(Contact::getState)); // Groups contacts by their State field
	}

	// UC 10: Get count of persons by City
	public Map<String, Long> getCountByCity() {
		return addressBookSystem.values().stream().flatMap(book -> book.getContactList().stream())
				.collect(Collectors.groupingBy(Contact::getCity, Collectors.counting())); //
	}

	// UC 10: Get count of persons by State
	public Map<String, Long> getCountByState() {
		return addressBookSystem.values().stream().flatMap(book -> book.getContactList().stream())
				.collect(Collectors.groupingBy(Contact::getState, Collectors.counting())); //
	}

	private final String FILE_PATH = "src/main/resources/addressbook_data.txt";

	// UC 13: Write Address Book Data to File
	public String writeToFile() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
			for (Map.Entry<String, AddressBook> entry : addressBookSystem.entrySet()) {
				writer.write("AddressBook: " + entry.getKey() + "\n");
				for (Contact contact : entry.getValue().getContactList()) {
					writer.write(contact.toString() + "\n");
				}
				writer.write("---\n");
			}
			return "Data successfully written to " + FILE_PATH;
		} catch (IOException e) {
			return "Error writing to file: " + e.getMessage();
		}
	}
	// UC 13: Read Address Book Data from File (Basic Implementation)
	public List<String> readFromFile() {
		try {
			return Files.readAllLines(Paths.get(FILE_PATH));
		} catch (IOException e) {
			return Collections.singletonList("Error reading file: " + e.getMessage());
		}
	}
}