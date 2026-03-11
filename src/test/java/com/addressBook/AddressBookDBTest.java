package com.addressBook;
 
import com.addressBook.model.Contact;
import com.addressBook.service.AddressBookDBService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

public class AddressBookDBTest {

	@Test
	public void givenAddressBookInDB_WhenRetrieved_ShouldMatchContactCount() {
		AddressBookDBService dbService = new AddressBookDBService();
		List<Contact> contactList = dbService.readData();

		// Assert that the list is retrieved (matches your current DB entries count)
		Assertions.assertEquals(1, contactList.size());
	}
	//UC17
	@Test
	public void givenNewCityForContact_WhenUpdated_ShouldSyncWithDB() {
	    AddressBookDBService dbService = new AddressBookDBService();
	    String name = "Anand";
	    String newCity = "Indore";

	    // 1. Update in DB
	    dbService.updateContactCity(name, newCity);

	    // 2. Retrieve updated contact from DB using PreparedStatement
	    List<Contact> dbData = dbService.readData();
	    Contact dbContact = dbData.stream()
	            .filter(c -> c.getFirstName().equals(name))
	            .findFirst().get();

	    // 3. Compare with a locally created contact to check sync
	    // (Assumes you have implemented Equals in your Contact model)
	    assertEquals(newCity, dbContact.getCity());
	}
}