package com.addressBook;
 
import com.addressBook.model.AddressBook;
import com.addressBook.model.Contact;
import com.addressBook.service.AddressBookService;
import com.addressBook.service.ContactService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Collections;
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
}