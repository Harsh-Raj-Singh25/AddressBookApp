package com.addressBook.controller;


import com.addressBook.model.Contact; 
import com.addressBook.service.ContactService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addressbook")
public class ContactController {

	@Autowired
	private ContactService service;

	// UC 2: Add Contact (POST)
	@PostMapping("/add")
	public String addContact(@RequestBody Contact contact) {
		return service.addContact(contact);
	}

	// GET all contacts
	@GetMapping("/all")
	public List<Contact> getAll() {
		return service.getAllContacts();
	}

	// UC 3: Edit Contact (PUT)
	@PutMapping("/edit/{firstName}/{lastName}")
	public String editContact(@PathVariable String firstName, @PathVariable String lastName,
			@RequestBody Contact updatedContact) {
		return service.editContact(firstName, lastName, updatedContact);
	}

	// UC 4: Delete Contact (DELETE)
	@DeleteMapping("/delete/{firstName}/{lastName}")
	public String deleteContact(@PathVariable String firstName, @PathVariable String lastName) {
		return service.deleteContact(firstName, lastName);
	}
	// UC 5: Add Multiple Contacts (POST a List)
	@PostMapping("/add-multiple")
	public String addMultiple(@RequestBody List<Contact> contacts) {
	    return service.addMultipleContacts(contacts);
	}
}
