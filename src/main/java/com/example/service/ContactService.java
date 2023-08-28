package com.example.service;

import com.example.entities.Contact;
import com.example.entities.Student;
import com.example.exeption.ContactNotFound;
import com.example.exeption.StudentNotFound;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ContactService {
    public Contact saveContact(Contact newContact);
    public Contact getContactById(Long idContact) throws ContactNotFound;
    public List<Contact> getAllContacts();
    public void deleteContact(Long idContact);
    public ResponseEntity<Contact> updateContact(Long idContact, Contact contactDetails);

    // ... Methods for querying and filtering Contacts based on different criteria

    public List<Contact> getContactsByIdStudent(Long idStudent) throws StudentNotFound;
    public Student addContactToStudent(Long idStudent, Contact contact);
    
}
