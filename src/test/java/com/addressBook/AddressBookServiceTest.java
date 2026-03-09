package com.addressBook;

import com.addressBook.model.AddressBook;
import com.addressBook.model.Contact;
import com.addressBook.service.AddressBookService;
import com.addressBook.service.ContactService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class AddressBookServiceTest {

	private AddressBookService abService;
	private ContactService contactService;

	@BeforeEach
	void setup() {
		// Initialize services before each test
		abService = new AddressBookService();
		contactService = new ContactService();
	}

	@Test
	public void testMultipleAddressBooksIndependence() {
		// 1. Create two separate books
		abService.createNewAddressBook("Personal");
		abService.createNewAddressBook("Work");

		// 2. Prepare two different contacts
		Contact personalContact = new Contact("Harsh", "Raj", "Street 1", "Bhopal", "MP", "462001", "9876543210",
				"h@p.com");
		Contact workContact = new Contact("John", "Doe", "Office 1", "Indore", "MP", "452001", "1234567890", "j@w.com");

		// 3. Add contacts to their respective books
		AddressBook personalBook = abService.getAddressBook("Personal");
		AddressBook workBook = abService.getAddressBook("Work");

		contactService.addContacts(personalBook, Collections.singletonList(personalContact));
		contactService.addContacts(workBook, Collections.singletonList(workContact));

		// 4. Assertions (The proof)
		// Check that "Personal" has 1 contact and it's Harsh
		assertEquals(1, abService.getAddressBook("Personal").getContactList().size());
		assertEquals("Harsh", abService.getAddressBook("Personal").getContactList().get(0).getFirstName());

		// Check that "Work" has 1 contact and it's John
		assertEquals(1, abService.getAddressBook("Work").getContactList().size());
		assertEquals("John", abService.getAddressBook("Work").getContactList().get(0).getFirstName());

		// Ensure Harsh is NOT in the Work book
		assertNotEquals("Harsh", abService.getAddressBook("Work").getContactList().get(0).getFirstName());
	}

	@Test
	public void testBookExistence() {
		abService.createNewAddressBook("Family");

		// Assert that the book exists in the map
		assertNotNull(abService.getAddressBook("Family"));

		// Assert that a non-existent book returns null
		assertNull(abService.getAddressBook("NonExistentBook"));
	}

	// UC7
	@Test
	public void testDuplicateCheck() {
		AddressBook book = new AddressBook("TestBook");
		Contact c1 = new Contact("Harsh", "Raj", "Addr1", "City", "ST", "123", "999", "h@t.com");
		Contact c2 = new Contact("Harsh", "Raj", "DifferentAddr", "City", "ST", "123", "888", "h2@t.com");

		// First add should succeed
		String result1 = contactService.addContactSecurely(book, c1);
		assertEquals("Contact added successfully.", result1);

		// Second add with same name should fail (UC 7)
		String result2 = contactService.addContactSecurely(book, c2);
		assertEquals("Duplicate Entry! Harsh already exists in this book.", result2);
		assertEquals(1, book.getContactList().size()); // Ensure only one was added
	}

	// uc8
	@Test
	public void testSearchByCityAcrossMultipleBooks() {
		// 1. Create multiple books
		abService.createNewAddressBook("Personal");
		abService.createNewAddressBook("Work");

		// 2. Add people in the same city to DIFFERENT books
		Contact c1 = new Contact("Harsh", "Raj", "Street 1", "Bhopal", "MP", "462001", "98765", "h@p.com");
		Contact c2 = new Contact("Amit", "Sharma", "Street 2", "Bhopal", "MP", "462022", "11111", "a@w.com");
		Contact c3 = new Contact("John", "Doe", "Wall St", "New York", "NY", "10001", "22222", "j@n.com");

		contactService.addContacts(abService.getAddressBook("Personal"), Collections.singletonList(c1));
		contactService.addContacts(abService.getAddressBook("Work"), Collections.singletonList(c2));
		contactService.addContacts(abService.getAddressBook("Work"), Collections.singletonList(c3));

		// 3. Perform the search across all books (UC 8)
		List<Contact> bhopalResults = abService.searchByCity("Bhopal");

		// 4. Assertions
		assertEquals(2, bhopalResults.size(), "Should find 2 people in Bhopal across all books");
		assertTrue(bhopalResults.stream().anyMatch(c -> c.getFirstName().equals("Harsh")));
		assertTrue(bhopalResults.stream().anyMatch(c -> c.getFirstName().equals("Amit")));

		// Ensure New York resident is not in the list
		assertFalse(bhopalResults.stream().anyMatch(c -> c.getFirstName().equals("John")));
	}

	// UC9
	@Test
	public void testGroupingByCity() {
		abService.createNewAddressBook("A");
		Contact c1 = new Contact("Harsh", "Raj", "S1", "Bhopal", "MP", "1", "1", "h@t.com");
		Contact c2 = new Contact("Amit", "S", "S2", "Bhopal", "MP", "2", "2", "a@t.com");

		contactService.addContacts(abService.getAddressBook("A"), Arrays.asList(c1, c2));

		Map<String, List<Contact>> cityMap = abService.viewByCity();

		// Assert that "Bhopal" key exists and contains exactly 2 people
		assertTrue(cityMap.containsKey("Bhopal"));
		assertEquals(2, cityMap.get("Bhopal").size());
	}

	// UC10
	@Test
	public void testCountByCity() {
		abService.createNewAddressBook("StatsBook");
		AddressBook book = abService.getAddressBook("StatsBook");

		contactService.addContacts(book,
				Arrays.asList(new Contact("User1", "L1", "A1", "Bhopal", "MP", "1", "1", "u1@t.com"),
						new Contact("User2", "L2", "A2", "Bhopal", "MP", "2", "2", "u2@t.com"),
						new Contact("User3", "L3", "A3", "Indore", "MP", "3", "3", "u3@t.com")));

		Map<String, Long> counts = abService.getCountByCity();

		assertEquals(2, counts.get("Bhopal")); //
		assertEquals(1, counts.get("Indore")); //
	}

	// Uc13
	@Test
	public void testFileCreation() {
		abService.createNewAddressBook("IO-Test");
		abService.writeToFile();

		java.io.File file = new java.io.File("src/main/resources/addressbook_data.txt");
		assertTrue(file.exists(), "The persistence file should be created on disk.");
	}
	//UC14
	@Test
	public void testCSVRoundTrip() {
		abService.createNewAddressBook("CSVBook");
		Contact c1 = new Contact("Harsh", "Raj", "MP Nagar", "Bhopal", "MP", "462001", "98765", "h@t.com");
		abService.getAddressBook("CSVBook").getContactList().add(c1);

		// Write to CSV
		abService.writeToCSV();

		// Read from CSV
		List<Contact> importedContacts = abService.readFromCSV();

		assertFalse(importedContacts.isEmpty());
		assertEquals("Harsh", importedContacts.get(0).getFirstName());
		assertEquals("Bhopal", importedContacts.get(0).getCity());
	}
}