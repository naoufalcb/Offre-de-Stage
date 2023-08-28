package com.example.service;

import com.example.entities.*;
import com.example.exeption.SkillNotFound;
import com.example.exeption.StudentNotFound;
import com.example.security.Account;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StudentService {
    //----------------------POST----------------------
    public Student createStudent(Student newStudent);
    public Student createStudentAccount(Student newStudent,Account newAccount);
    //----------------------PUT----------------------
    public ResponseEntity<Student> updateStudent(Long idStudent,Student studentDetails);


    //----------------------GET----------------------
    public List<Student> getAllStudents();
    public Student getStudentById(Long idStudent) throws StudentNotFound;
    public List<Skill> getStudentSkills(Long idStudent) throws StudentNotFound;
    public List<Education> getStudentEducations(Long idStudent) throws StudentNotFound;
    public List<Certificate> getStudentCertificates(Long idStudent) throws StudentNotFound;
    public List<Language> getStudentLanguages(Long idStudent) throws StudentNotFound;
    public Location getStudentLocation(Long idStudent) throws StudentNotFound;
    public List<Contact> getStudentContacts(Long idStudent) throws StudentNotFound;
    public List<Experience> getStudentExperiences(Long idStudent) throws StudentNotFound;

    //----------------------DELETE----------------------
    public void deleteStudent(Long idStudent);

    //... Verification
    boolean existsStudent(Long idStudent);

}
