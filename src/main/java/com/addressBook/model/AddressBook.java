package com.addressBook.model; 

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class AddressBook {
	private String bookName;
	private List<Contact> contactList = new ArrayList<>();

	public AddressBook(String name) {
		this.bookName = name;
	}
}