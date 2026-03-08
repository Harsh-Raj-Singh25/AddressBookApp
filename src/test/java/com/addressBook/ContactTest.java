package com.addressBook;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.addressBook.model.Contact; 
import com.addressBook.service.ContactService;

public class ContactTest {
	@Test
	public void testEquality() {
		Contact c1 = new Contact("Harsh", "Raj", "Press", "Bhopal", "MP", "57545", "89542178542", "harsh252@gmail.com");
		Contact c2 = new Contact("Harsh", "Raj", "Press", "Bhopal", "MP", "57545", "89542178542", "harsh252@gmail.com");
//		assertEquals(c1,c2);
		assertTrue(c1.equals(c2));
	}

	@Test
	public void testInequality() {
		Contact c1 = new Contact("Harsh", "Raj", "Press", "Bhopal", "MP", "57545", "89542178542", "harsh252@gmail.com");
		Contact c2 = new Contact("Aman", "Sharma", "Press", "Bhopal", "MP", "57545", "89542178542", "aman@gmail.com");

		// Check that different names result in 'false' for equality
		assertFalse(c1.equals(c2), "Contacts with different names should not be equal");
	}

	@Test
	public void testToStringFormat() {
		Contact c1 = new Contact("Harsh", "Raj", "Press", "Bhopal", "MP", "57545", "89542178542", "harsh252@gmail.com");
		String expected = "Harsh LastName : Raj Address : Press City : BhopalState : MP Zip: 57545PhoneNumber :89542178542 Email :harsh252@gmail.com";

		assertEquals(expected, c1.toString(),
				"The toString output should match the custom format defined in the class");
	}

	@Test
	public void testGetterMethods() {
		Contact c1 = new Contact("Harsh", "Raj", "Press", "Bhopal", "MP", "57545", "89542178542", "harsh252@gmail.com");

		// Check if names match exactly (Case Sensitive)
		assertEquals("Harsh", c1.getFirstName());
		assertEquals("Raj", c1.getLastName());
		assertEquals("89542178542", c1.getPhoneNumber());
	}

	@Test
	public void testEqualityWithNull() {
		Contact c1 = new Contact("Harsh", "Raj", "Press", "Bhopal", "MP", "57545", "89542178542", "harsh252@gmail.com");

		// Testing against null should not throw an error, it should just return false
		assertFalse(c1.equals(null));
	}

	// Deletion test
	@Test
	public void testDeleteContact() {
		ContactService service = new ContactService();
		Contact c1 = new Contact("Harsh", "Raj", "Press", "Bhopal", "MP", "57545", "89542178542", "harsh252@gmail.com");
		service.addContact(c1);
		// Initial size should be 1
		assertEquals(1, service.getAllContacts().size());
		// Delete the contact
		service.deleteContact("Harsh", "Raj");
		// Final size should be 0
		assertEquals(0, service.getAllContacts().size());
	}

	// Modify test
	@Test
	public void testEditContact() {
		ContactService service = new ContactService();
		Contact c1 = new Contact("Harsh", "Raj", "Press", "Bhopal", "MP", "57545", "89542178542", "harsh252@gmail.com");
		service.addContact(c1);

		Contact updatedInfo = new Contact("Harsh", "Raj", "New Road", "Indore", "MP", "452001", "9999999999",
				"new@gmail.com");
		service.editContact("Harsh", "Raj", updatedInfo);

		assertEquals("New Road", service.getAllContacts().get(0).getAddress());
		assertEquals("Indore", service.getAllContacts().get(0).getCity());
	}

	// Addition of multiple contacts test
	@Test
	public void testAddMultipleContacts() {
		ContactService service = new ContactService();
		List<Contact> bulkList = new ArrayList<>();

		bulkList.add(new Contact("Harsh", "Raj", "Street 1", "Bhopal", "MP", "111", "999", "h@test.com"));
		bulkList.add(new Contact("Aman", "Kumar", "Street 2", "Delhi", "DL", "222", "888", "a@test.com"));

		service.addMultipleContacts(bulkList);

		// Check if the list size is exactly 2
		assertEquals(2, service.getAllContacts().size());
	}

	// UC2 test
	@Test
	public void testAddContactToList() {
		ContactService service = new ContactService();
		Contact c1 = new Contact("Harsh", "Raj", "Press", "Bhopal", "MP", "57545", "89542178542", "harsh252@gmail.com");

		service.addContact(c1);

		assertEquals(1, service.getAllContacts().size());
		assertEquals("Harsh", service.getAllContacts().get(0).getFirstName());
	}
}
