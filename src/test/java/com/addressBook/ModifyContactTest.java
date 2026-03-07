
package com.addressBook;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.addressBook.model.Contact;
import com.addressBook.service.AddressBookService;

public class ModifyContactTest {
	@Test
	public void testEditContact() {
		AddressBookService service = new AddressBookService();
		Contact c1 = new Contact("Harsh", "Raj", "Press", "Bhopal", "MP", "57545", "89542178542", "harsh252@gmail.com");
		service.addContact(c1);

		Contact updatedInfo = new Contact("Harsh", "Raj", "New Road", "Indore", "MP", "452001", "9999999999",
				"new@gmail.com");
		service.editContact("Harsh", "Raj", updatedInfo);

		assertEquals("New Road", service.getAllContacts().get(0).getAddress());
		assertEquals("Indore", service.getAllContacts().get(0).getCity());
	}
}
