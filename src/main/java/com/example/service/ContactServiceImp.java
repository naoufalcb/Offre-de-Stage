package com.example.service;

import com.example.entities.Contact;
import com.example.entities.Student;
import com.example.exeption.ContactNotFound;
import com.example.exeption.StudentNotFound;
import com.example.repository.ContactRepository;
import com.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImp implements ContactService{
    @Autowired
    ContactRepository contactRepository;
    @Autowired
    StudentRepository studentRepository;

    @Override
    public Contact saveContact(Contact newContact) {
        return contactRepository.save(newContact);
    }

    @Override
    public Contact getContactById(Long idContact) {
        return contactRepository.findById(idContact)
                .orElseThrow(()-> new ContactNotFound("Contact with Id : "+idContact+" Not Found. "));
    }

    @Override
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    @Override
    public void deleteContact(Long idContact) {
        contactRepository.deleteById(idContact);
    }

    // ... Methods for querying and filtering contacts based on different criteria

    @Override
    public List<Contact> getContactsByIdStudent(Long idStudent) throws StudentNotFound{
        return contactRepository.findByIdStudent(idStudent);
    }

    public Student addContactToStudent(Long idStudent, Contact contact) {
        Student student = studentRepository.findById(idStudent).orElse(null);
        if (student != null) {
            contact.setStudent(student);
            student.getContacts().add(contact);
            studentRepository.save(student);
        }
        return student;
    }

    @Override
    public ResponseEntity<Contact> updateContact(Long idContact, Contact contactDetails) {
        Contact updateContact = contactRepository.findById(idContact).get();

        updateContact.setPhone(contactDetails.getPhone());
        updateContact.setEmail(contactDetails.getEmail());
        updateContact.setAddress(contactDetails.getAddress());
        updateContact.setWebsite(contactDetails.getWebsite());

        contactRepository.save(updateContact);
        return ResponseEntity.ok(updateContact);
    }

}
