package com.addressBook.service; 

import com.addressBook.model.AddressBook;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class AddressBookService {
    // UC 6: Dictionary of Address Book Name to Address Book Object
    private Map<String, AddressBook> addressBookSystem = new HashMap<>();

    public String createNewAddressBook(String name) {
        if (addressBookSystem.containsKey(name)) return "Already exists";
        addressBookSystem.put(name, new AddressBook(name));
        return "Created Address Book: " + name;
    }

    public AddressBook getAddressBook(String name) {
        return addressBookSystem.get(name);
    }

    public Set<String> listAllBooks() {
        return addressBookSystem.keySet();
    }
}