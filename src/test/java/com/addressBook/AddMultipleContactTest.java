package com.addressBook;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.addressBook.model.Contact;
import com.addressBook.service.AddressBookService;

public class AddMultipleContactTest {
	@Test
	public void testAddMultipleContacts() {
		AddressBookService service = new AddressBookService();
		List<Contact> bulkList = new ArrayList<>();

		bulkList.add(new Contact("Harsh", "Raj", "Street 1", "Bhopal", "MP", "111", "999", "h@test.com"));
		bulkList.add(new Contact("Aman", "Kumar", "Street 2", "Delhi", "DL", "222", "888", "a@test.com"));

		service.addMultipleContacts(bulkList);

		// Check if the list size is exactly 2
		assertEquals(2, service.getAllContacts().size());
	}
}
