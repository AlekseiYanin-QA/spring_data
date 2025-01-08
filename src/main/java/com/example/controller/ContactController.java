package com.example.controller;

import com.example.model.Contact;
import com.example.dao.ContactDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    private final ContactDao contactDao;

    @Autowired
    public ContactController(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    @GetMapping
    public List<Contact> getAllContacts() {
        return contactDao.getAllContacts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable Long id) {
        Optional<Contact> contact = contactDao.getContactById(id);
        return contact.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Contact> addContact(@RequestBody Contact contact) {
        Contact savedContact = contactDao.addContact(contact);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedContact);
    }

    @PutMapping("/{id}/phone")
    public ResponseEntity<Void> updatePhone(@PathVariable Long id, @RequestParam String phone) {
        contactDao.updatePhone(id, phone);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/email")
    public ResponseEntity<Void> updateEmail(@PathVariable Long id, @RequestParam String email) {
        contactDao.updateEmail(id, email);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        contactDao.deleteContact(id);
        return ResponseEntity.noContent().build();
    }
}