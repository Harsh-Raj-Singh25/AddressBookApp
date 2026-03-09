package com.addressBook;

import com.addressBook.model.AddressBook;
import com.addressBook.model.Contact;
import com.addressBook.service.AddressBookService;
import com.addressBook.service.ContactService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class AddressBookMain {
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(AddressBookMain.class, args);

		// Retrieve both separate services from the Spring Context
		AddressBookService abService = context.getBean(AddressBookService.class);
		ContactService contactService = context.getBean(ContactService.class);

		// 1. UC 6: Use AddressBookService to create separate books
		abService.createNewAddressBook("Personal");
		abService.createNewAddressBook("Work");

		// 2. UC 2 & 5: Create some contacts
		Contact c1 = new Contact("Harsh", "Raj", "MP Nagar", "Bhopal", "MP", "462001", "9876543210", "harsh@test.com");
		Contact c2 = new Contact("John", "Doe", "Baker St", "London", "UK", "NW1", "123456789", "john@test.com");

		// 3. Coordination: Get the specific book and use ContactService to add data
		AddressBook personalBook = abService.getAddressBook("Personal");
		AddressBook workBook = abService.getAddressBook("Work");

		if (personalBook != null) {
			contactService.addContacts(personalBook, Arrays.asList(c1));
			System.out.println("Added Harsh to Personal Book.");
		}

		if (workBook != null) {
			contactService.addContacts(workBook, Arrays.asList(c2));
			System.out.println("Added John to Work Book.");
		}

		// 4. UC 3: Use ContactService to edit a contact inside a specific book
		Contact updatedHarsh = new Contact("Harsh", "Raj", "Arera Colony", "Bhopal", "MP", "462016", "9000000000",
				"harsh_new@test.com");
		contactService.updateContact(personalBook, "Harsh", "Raj", updatedHarsh);

		// 5. Final Print to verify the Dictionary Requirement
		System.out.println("\n--- Final System State ---");
		abService.listAllBooks().forEach(bookName -> {
			System.out.println(
					"Book: " + bookName + " | Contacts: " + abService.getAddressBook(bookName).getContactList().size());
		});

		// UC 7
		// 1. Create a book
		abService.createNewAddressBook("Personal");
		AddressBook myBook = abService.getAddressBook("Personal");
		// 2. Create two identical contacts (Same Name)
		Contact c3 = new Contact("Harsh", "Raj", "MP Nagar", "Bhopal", "MP", "462001", "98765", "h@t.com");
		Contact c4 = new Contact("Harsh", "Raj", "Arera", "Bhopal", "MP", "462016", "11111", "copy@t.com");
		// 3. Try adding both
		System.out.println("--- UC 7: Duplicate Check Test ---");
		System.out.println("First Attempt: " + contactService.addContactSecurely(myBook, c3));
		System.out.println("Second Attempt: " + contactService.addContactSecurely(myBook, c4)); // Should fail
		System.out.println("Total contacts in Personal: " + myBook.getContactList().size());

		// UC8
		System.out.println("\n--- UC 8: Searching Across All Books ---");
		// Searching for anyone in "Bhopal" regardless of which book they are in
		List<Contact> bhopalResidents = abService.searchByCity("Bhopal");
		System.out.println("People found in Bhopal: " + bhopalResidents.size());
		bhopalResidents.forEach(person -> System.out.println(person.getFirstName() + " " + person.getLastName()));

		// UC9
		System.out.println("\n--- UC 9: Viewing Dictionary by City ---");
		Map<String, List<Contact>> cityDictionary = abService.viewByCity();
		cityDictionary.forEach((city, people) -> {
			System.out.println("City: " + city + " | Residents: " + people.size());
			people.forEach(p -> System.out.println(" - " + p.getFirstName()));
		});

		// UC10
		System.out.println("\n--- UC 10: Contact Statistics ---");
		Map<String, Long> cityCounts = abService.getCountByCity();
		cityCounts.forEach((city, count) -> System.out.println("City: " + city + " | Total Contacts: " + count));

		// using UC 6: Create an Address Book
		abService.createNewAddressBook("TravelLog");
		AddressBook book = abService.getAddressBook("TravelLog");

		// using UC 2 & 5: Add contacts with different locations
		List<Contact> contacts = Arrays.asList(
				new Contact("Zoya", "Khan", "Street 1", "Bhopal", "MP", "462001", "98765", "z@t.com"),
				new Contact("Amit", "Sharma", "Street 2", "Indore", "MP", "452001", "11111", "a@t.com"),
				new Contact("Namrata", "Khapre", "St.Paul road", "Pandhurna", "MP", "10001", "22222", "n@n.com"));
		contactService.addContacts(book, contacts);

		// UC 11 & 12: Sorting demonstration
		// UC11
		System.out.println("\n--- UC 11: Sorted Address Book Entries according to name ---"); 
		if (book != null) {
			List<Contact> sortedList = contactService.getSortedContacts(book);

			// Prints the person entry using the overridden toString() method
			sortedList.forEach(System.out::println);
		}
		//UC12
		System.out.println("\n--- UC 12: Sorting by ZIP Code ---");
		List<Contact> sortedByZip = contactService.getSortedByField(book, "zip");
		sortedByZip.forEach(System.out::println);

		System.out.println("\n--- UC 12: Sorting by City ---");
		List<Contact> sortedByCity = contactService.getSortedByField(book, "city");
		sortedByCity.forEach(System.out::println);
	}
}