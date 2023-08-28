package com.example.controller;

import com.example.entities.Contact;
import com.example.entities.Student;
import com.example.exeption.ContactNotFound;
import com.example.exeption.StudentNotFound;
import com.example.service.ContactService;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    ContactService contactService;

    @PostMapping("/student/{idStudent}")
    public ResponseEntity<String> addContactToStudent(
            @PathVariable Long idStudent,
            @RequestBody Contact contact) {
        Student updatedStudent = contactService.addContactToStudent(idStudent, contact);
        if (updatedStudent != null) {
            return ResponseEntity.ok("Contact created for Student "+idStudent+" Successfully. ");
        } else {
            throw new StudentNotFound("Student with Id : "+idStudent+" Not Found. ");
        }
    }

    @GetMapping("/{idContact}")
    public ResponseEntity<Contact> getContactById(@PathVariable Long idContact) {
        Contact contact = contactService.getContactById(idContact);
        if (contact != null) {
            return ResponseEntity.ok(contact);
        } else {
            throw new ContactNotFound("Contact with Id : " + idContact + " Not Found. ");
        }
    }

    @GetMapping("/getAllContacts")
    public ResponseEntity<List<Contact>> getAllContacts() throws ContactNotFound{
        List<Contact> contacts = contactService.getAllContacts();
        if (!contacts.isEmpty()) {
            return ResponseEntity.ok(contacts);
        } else {
            throw new ContactNotFound("No Contact Found. ");
        }
    }

    @PutMapping("/{idContact}")
    public ResponseEntity<Contact> updateContact(@PathVariable Long idContact, @RequestBody Contact updatedContact) {
        Contact contact = contactService.getContactById(idContact);
        if (contact != null) {
            contactService.updateContact(idContact,updatedContact);
            return ResponseEntity.ok(contact);
        } else {
            throw new ContactNotFound("Contact with Id : " + idContact + " Not Found. ");
        }
    }

    @DeleteMapping("/{idContact}")
    public ResponseEntity<String> deleteContact(@PathVariable Long idContact) {
        Contact contact = contactService.getContactById(idContact);
        if (contact != null) {
            contactService.deleteContact(idContact);

            return ResponseEntity.ok("Contact "+idContact+" deleted successfully");
        } else {
            throw new ContactNotFound("Contact with Id : " + idContact + " Not Found. ");
        }
    }



    @GetMapping("/student/{idStudent}")
    public ResponseEntity<List<Contact>> getContactsByIdStudent(@PathVariable Long idStudent) {
        List<Contact> contacts = contactService.getContactsByIdStudent(idStudent);
        if (!contacts.isEmpty()) {
            return ResponseEntity.ok(contacts);
        } else {
            throw new StudentNotFound("Student with Id : " + idStudent + " Not Found. ");
        }
    }


}
