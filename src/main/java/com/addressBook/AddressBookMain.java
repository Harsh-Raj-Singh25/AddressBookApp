package com.addressBook;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.addressBook.model.Contact;

@SpringBootApplication
public class AddressBookMain {
	public static void main(String[] args) {
		Contact c1=new Contact("Harsh","Raj","Press","Bhopal","MP","57545","89542178542","harsh252@gmail.com");
		System.out.println(c1);
	}
}
