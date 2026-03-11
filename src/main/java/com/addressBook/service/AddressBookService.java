package com.addressBook.service;

import com.addressBook.model.AddressBook;
import com.addressBook.model.Contact;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.io.Writer;

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

	private final Path CSV_PATH = Paths.get("src", "main", "resources", "contacts.csv");

	// UC 14: Write Contacts to CSV using OpenCSV
	public String writeToCSV() {
		try {
			Files.createDirectories(CSV_PATH.getParent());
			try (Writer writer = Files.newBufferedWriter(CSV_PATH)) {
				StatefulBeanToCsv<Contact> beanToCsv = new StatefulBeanToCsvBuilder<Contact>(writer).build();

				// Collect all contacts across books to save into one CSV
				List<Contact> allContacts = addressBookSystem.values().stream()
						.flatMap(book -> book.getContactList().stream()).toList();

				beanToCsv.write(allContacts);
				return "CSV saved successfully at: " + CSV_PATH.toAbsolutePath();
			}
		} catch (Exception e) {
			return "CSV Write Error: " + e.getMessage();
		}
	}

	// UC 14: Read Contacts from CSV
	public List<Contact> readFromCSV() {
		try (Reader reader = Files.newBufferedReader(CSV_PATH)) {
			return new CsvToBeanBuilder<Contact>(reader).withType(Contact.class).build().parse();
		} catch (IOException e) {
			return Collections.emptyList();
		}
	}

	private final Path JSON_PATH = Paths.get("src", "main", "resources", "contacts.json");
	private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

	// UC 15: Write Address Book data to JSON file using GSON
	public String writeToJSON() {
		try {
			Files.createDirectories(JSON_PATH.getParent());
			try (Writer writer = Files.newBufferedWriter(JSON_PATH)) {
				// We serialize the entire map to preserve the book names
				gson.toJson(addressBookSystem, writer);
				return "JSON file created successfully at: " + JSON_PATH.toAbsolutePath();
			}
		} catch (IOException e) {
			return "JSON Write Error: " + e.getMessage();
		}
	}

	// UC 15: Read Address Book data from JSON file
	public Map<String, AddressBook> readFromJSON() {
		try (Reader reader = Files.newBufferedReader(JSON_PATH)) {
			java.lang.reflect.Type type = new TypeToken<Map<String, AddressBook>>() {
			}.getType();
			Map<String, AddressBook> importedData = gson.fromJson(reader, type);
			if (importedData != null) {
				this.addressBookSystem = importedData;
			}
			return this.addressBookSystem;
		} catch (IOException e) {
			return Collections.emptyMap();
		}
	}

	// UC 17 - jdbc contact update
	private AddressBookDBService dbService = new AddressBookDBService();
	private Map<String, AddressBook> addressBookSystem1 = new HashMap<>();

	// UC 17: Method to update DB and Sync Local Memory
	public void updateAndSyncContact(String bookName, String contactName, String newCity) {
		// 1. Update the Database first
		int rowsAffected = dbService.updateContactCity(contactName, newCity);

		// 2. If DB update is successful, sync the memory
		if (rowsAffected > 0) {
			AddressBook localBook = addressBookSystem1.get(bookName);
			if (localBook != null) {
				localBook.getContactList().stream().filter(c -> c.getFirstName().equalsIgnoreCase(contactName))
						.forEach(c -> c.setCity(newCity));
				System.out.println("Sync Successful: Memory and DB are now aligned.");
			}
		} else {
			System.out.println("Sync Failed: Database was not updated.");
		}
	}
}