package com.addressBook.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.addressBook.model.Contact;
import com.addressBook.service.AddressBookDBService;
import com.addressBook.service.AddressBookService;

@RestController
@RequestMapping("/system/db")
public class AddressBookDBController {

	private final AddressBookDBService dbService = new AddressBookDBService();
	private final AddressBookService abService=new AddressBookService();
	@GetMapping("/retrieve")
	public List<Contact> getAllFromDB() {
		return dbService.readData(); // Returns JSON to Postman
	}

	@PutMapping("/{bookName}/updateCity")
	public String updateCity(@PathVariable String bookName, @RequestParam String name, @RequestParam String city) {
		abService.updateAndSyncContact(bookName, name, city);
		return "Update and Sync complete for " + name;
	}
}