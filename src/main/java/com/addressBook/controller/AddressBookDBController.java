package com.addressBook.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.addressBook.model.Contact;
import com.addressBook.service.AddressBookDBService;

@RestController
@RequestMapping("/system/db")
public class AddressBookDBController {

	private final AddressBookDBService dbService = new AddressBookDBService();

	@GetMapping("/retrieve")
	public List<Contact> getAllFromDB() {
		return dbService.readData(); // Returns JSON to Postman
	}
}