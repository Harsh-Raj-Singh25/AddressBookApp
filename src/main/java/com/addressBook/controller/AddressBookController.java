package com.addressBook.controller; 

import com.addressBook.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
}