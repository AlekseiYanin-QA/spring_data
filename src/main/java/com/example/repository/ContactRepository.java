package com.example.repository;

import com.example.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    @Modifying
    @Transactional // Добавьте эту аннотацию
    @Query("UPDATE Contact c SET c.phone = ?2 WHERE c.id = ?1")
    void updatePhone(Long id, String phone);

    @Modifying
    @Transactional // Добавьте эту аннотацию
    @Query("UPDATE Contact c SET c.email = ?2 WHERE c.id = ?1")
    void updateEmail(Long id, String email);
}