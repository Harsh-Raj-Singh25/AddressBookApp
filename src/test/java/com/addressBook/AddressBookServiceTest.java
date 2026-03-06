package com.addressBook;

import com.addressBook.model.Contact;
import com.addressBook.service.AddressBookService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressBookServiceTest {

	@Test
	public void testAddContactToList() {
		AddressBookService service = new AddressBookService();
		Contact c1 = new Contact("Harsh", "Raj", "Press", "Bhopal", "MP", "57545", "89542178542", "harsh252@gmail.com");

		service.addContact(c1);

		assertEquals(1, service.getAllContacts().size());
		assertEquals("Harsh", service.getAllContacts().get(0).getFirstName());
	}
}