package com.addressBook;
 
import com.addressBook.model.Contact;
import com.addressBook.service.AddressBookDBService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;

public class AddressBookDBTest {

	@Test
	public void givenAddressBookInDB_WhenRetrieved_ShouldMatchContactCount() {
		AddressBookDBService dbService = new AddressBookDBService();
		List<Contact> contactList = dbService.readData();

		// Assert that the list is retrieved (matches your current DB entries count)
		Assertions.assertEquals(1, contactList.size());
	}
}