package com.example.dao;

import com.example.model.Contact;
import com.example.repository.ContactRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ContactDao {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactDao(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    // Получение всех контактов
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    // Получение контакта по ID
    public Optional<Contact> getContactById(Long id) {
        return contactRepository.findById(id);
    }

    // Добавление нового контакта
    public Contact addContact(Contact contact) {
        return contactRepository.save(contact);
    }

    // Обновление телефонного номера
    @Transactional
    public void updatePhone(Long id, String phone) {
        contactRepository.findById(id).ifPresent(contact -> {
            contact.setPhone(phone);
            contactRepository.save(contact);
        });
    }

    // Обновление email
    @Transactional
    public void updateEmail(Long id, String email) {
        contactRepository.findById(id).ifPresent(contact -> {
            contact.setEmail(email);
            contactRepository.save(contact);
        });
    }

    // Удаление контакта по ID
    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }
}