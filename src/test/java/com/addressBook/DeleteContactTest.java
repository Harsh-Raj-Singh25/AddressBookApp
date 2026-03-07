package com.addressBook;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.addressBook.model.Contact;
import com.addressBook.service.AddressBookService;

public class DeleteContactTest {
	@Test
	public void testDeleteContact() {
		AddressBookService service = new AddressBookService();
		Contact c1 = new Contact("Harsh", "Raj", "Press", "Bhopal", "MP", "57545", "89542178542", "harsh252@gmail.com");
		service.addContact(c1);
		// Initial size should be 1
		assertEquals(1, service.getAllContacts().size());
		// Delete the contact
		service.deleteContact("Harsh", "Raj");
		// Final size should be 0
		assertEquals(0, service.getAllContacts().size());
	}
}
