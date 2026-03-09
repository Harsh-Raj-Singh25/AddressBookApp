package com.addressBook.controller;

import com.addressBook.model.AddressBook;
import com.addressBook.model.Contact;
import com.addressBook.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/system")
public class AddressBookController {
	@Autowired
	private AddressBookService abService;

	@PostMapping("/create/{name}")
	public String create(@PathVariable String name) {
		return abService.createNewAddressBook(name);
	}

	@GetMapping("/list")
	public Set<String> list() {
		return abService.listAllBooks();
	}

	// UC 8: Search by City
	@GetMapping("/search/city/{city}")
	public List<Contact> searchCity(@PathVariable String city) {
		return abService.searchByCity(city);
	}

	// UC 8: Search by State
	@GetMapping("/search/state/{state}")
	public List<Contact> searchState(@PathVariable String state) {
		return abService.searchByState(state);
	}

	// UC 9: GET the dictionary of City -> List of Persons
	@GetMapping("/view/by-city")
	public Map<String, List<Contact>> viewByCity() {
		return abService.viewByCity();
	}

	// UC 9: GET the dictionary of State -> List of Persons
	@GetMapping("/view/by-state")
	public Map<String, List<Contact>> viewByState() {
		return abService.viewByState();
	}

	// UC 10: GET count by city
	@GetMapping("/count/by-city")
	public Map<String, Long> getCountByCity() {
		return abService.getCountByCity();
	}

	// UC 10: GET count by state
	@GetMapping("/count/by-state")
	public Map<String, Long> getCountByState() {
		return abService.getCountByState();
	}

	// UC 13: Save all books to file
	@PostMapping("/io/write")
	public String writeData() {
		return abService.writeToFile();
	}

	// UC 13: Read data from file
	@GetMapping("/io/read")
	public List<String> readData() {
		return abService.readFromFile();
	}

	// Change the mapping to be specific to CSV
	@PostMapping("/csv/write")
	public String writeCSV() {
		return abService.writeToCSV();
	}

	// Change the mapping to be specific to JSON
	@PostMapping("/json/write")
	public String saveJSON() {
		return abService.writeToJSON();
	}

	// Do the same for your READ methods to keep them organized
	@GetMapping("/csv/read")
	public List<Contact> readCSV() {
		return abService.readFromCSV();
	}

	@GetMapping("/json/read")
	public Map<String, AddressBook> loadJSON() {
		return abService.readFromJSON();
	}
}