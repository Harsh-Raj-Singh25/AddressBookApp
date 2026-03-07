package com.addressBook;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.addressBook.model.Contact;
import com.addressBook.service.AddressBookService;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class AddressBookMain {
	public static void main(String[] args) {
		// Start Spring Context
		ApplicationContext context = SpringApplication.run(AddressBookMain.class, args);

		// Get the Service Bean from Spring
		AddressBookService service = context.getBean(AddressBookService.class);

		// UC 2: Adding a new contact
		Contact newContact = new Contact("Harsh", "Raj", "Press", "Bhopal", "MP", "57545", "89542178542",
				"harsh252@gmail.com");
		service.addContact(newContact);

		// Displaying the output
		System.out.println("--- Address Book Contacts ---");
		service.getAllContacts().forEach(System.out::println);

		// UC3
		System.out.println("\n--- Editing Contact: Harsh Raj ---");

		// Create an object with NEW details
		Contact newData = new Contact("Harsh", "Raj", "New Street 10", "Indore", "MP", "452001", "9000000000",
				"harsh_new@gmail.com");

		// Call the edit method
		String result = service.editContact("Harsh", "Raj", newData);
		System.out.println(result);

		// Print list again to verify change
		service.getAllContacts().forEach(System.out::println);

		// UC4- delete contact by name 
		System.out.println("\n--- Deleting Contact: Harsh Raj ---");
		// Call the delete method
		String deleteResult = service.deleteContact("Harsh", "Raj");
		System.out.println(deleteResult);

		// Check if list is empty now
		System.out.println("Contacts remaining: " + service.getAllContacts().size());
	}
}
