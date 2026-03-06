package com.addressBook.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Contact {
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String phoneNumber;
	private String email;

	public Contact(String firstName, String lsName, String address, String city, String state, String zip,
			String phoneNumber, String email) {
		this.firstName = firstName;
		this.lastName = lsName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	@Override
	public String toString() {
		return firstName + " LastName : " + lastName + " Address : " + address + " City : " + city + "State : " + state
				+ " Zip: " + zip + "PhoneNumber :" + phoneNumber + " Email :" + email;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;

		if (!(o instanceof Contact))
			return false;

		Contact contact = (Contact) o;

		return firstName.equals(contact.firstName) && lastName.equals(contact.lastName);
	}
}
