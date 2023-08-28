package com.example.repository;

import com.example.entities.Contact;
import com.example.entities.Contact;
import com.example.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact,Long> {
    // Custom query to get Contacts by student id
    @Query("SELECT c FROM Contact c WHERE c.student.idStudent = :idStudent")
    List<Contact> findByIdStudent(Long idStudent);
}
