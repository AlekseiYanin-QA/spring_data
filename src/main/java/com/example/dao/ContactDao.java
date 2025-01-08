package com.example.dao;
import com.example.model.Contact;
import com.example.repository.ContactRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ContactDao {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactDao(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    public Contact getContactById(Long id) {
        return contactRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contact not found with id: " + id));
    }

    public Contact addContact(Contact contact) {
        return contactRepository.save(contact);
    }

    @Transactional
    public void updatePhone(Long id, String phone) {
        Contact contact = getContactById(id);
        contact.setPhone(phone);
        contactRepository.save(contact);
    }

    @Transactional
    public void updateEmail(Long id, String email) {
        Contact contact = getContactById(id);
        contact.setEmail(email);
        contactRepository.save(contact);
    }

    public void deleteContact(Long id) {
        if (!contactRepository.existsById(id)) {
            throw new EntityNotFoundException("Contact not found with id: " + id);
        }
        contactRepository.deleteById(id);
    }
}