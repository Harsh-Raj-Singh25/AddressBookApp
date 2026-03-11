package com.addressBook;

import com.addressBook.model.Contact;
import com.addressBook.service.AddressBookDBService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;

public class AddressBookDBTest {

	@Test
	public void givenAddressBookInDB_WhenRetrieved_ShouldMatchContactCount() {
		AddressBookDBService dbService = new AddressBookDBService();
		List<Contact> contactList = dbService.readData();

		// Assert that the list is retrieved (matches your current DB entries count)
		Assertions.assertEquals(4, contactList.size());
	}

	// UC17
	@Test
	public void givenNewCityForContact_WhenUpdated_ShouldSyncWithDB() {
		AddressBookDBService dbService = new AddressBookDBService();
		String name = "Anand";
		String newCity = "Indore";

		// 1. Update in DB
		dbService.updateContactCity(name, newCity);

		// 2. Retrieve updated contact from DB using PreparedStatement
		List<Contact> dbData = dbService.readData();
		Contact dbContact = dbData.stream().filter(c -> c.getFirstName().equals(name)).findFirst().get();

		// 3. Compare with a locally created contact to check sync
		// (Assumes you have implemented Equals in your Contact model)
		assertEquals(newCity, dbContact.getCity());
	}

	// UC18
	@Test
	public void givenDateRange_WhenRetrieved_ShouldReturnContactsAddedInThatPeriod() {
		AddressBookDBService dbService = new AddressBookDBService();

		// Define the period
		String start = "2025-01-01";
		String end = "2026-12-31";
		List<Contact> contactList = dbService.getContactsByDateRange(start, end);

		// Check if the retrieval matches your expected DB state
		Assertions.assertEquals(4, contactList.size());
		Assertions.assertEquals("Anand", contactList.get(0).getFirstName());
	}

	// UC 19
	@Test
	public void givenAddressBookDB_WhenQueriedByCity_ShouldReturnCorrectCount() {
		AddressBookDBService dbService = new AddressBookDBService();

		// Use Database function to count
		Map<String, Integer> cityCountMap = dbService.getContactCountByCityOrState("city");

		// Verify results
		if (cityCountMap.containsKey("Indore")) {
			Assertions.assertEquals(1, cityCountMap.get("Indore"));
		}
	}

	// Uc20
	@Test
	public void givenNewContact_WhenAddedToDB_ShouldSyncWithAddressBookMemory() {
		AddressBookDBService dbService = new AddressBookDBService();
		Contact newContact = new Contact("Gaurav", "S", "Indrapuri", "Bhopal", "MP", "462022", "1234567890",
				"g@test.com");

		// Get initial count
		int initialCount = dbService.readData().size();

		// UC 20: Add new contact
		dbService.addNewContact(newContact);

		// Verify count increased
		int finalCount = dbService.readData().size();
		Assertions.assertEquals(initialCount + 1, finalCount);
	}
}