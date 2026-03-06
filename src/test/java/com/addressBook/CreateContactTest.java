package com.addressBook;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;

import com.addressBook.model.Contact;

@Data
@AllArgsConstructor
public class CreateContactTest {

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
}
