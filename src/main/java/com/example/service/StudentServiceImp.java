package com.example.service;

import com.example.entities.*;
import com.example.exeption.StudentNotFound;
import com.example.repository.*;
import com.example.security.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


import java.util.List;

@Service
public class StudentServiceImp implements StudentService{

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    ExperienceRepository experienceRepository;


    //----------------------POST----------------------
    @Override
    public Student createStudent(Student newStudent) {
        return studentRepository.save(newStudent);
    }
    @Override
    public Student createStudentAccount(Student newStudent, Account newAccount) {

        return studentRepository.save(newStudent);
    }

    //----------------------PUT----------------------
    @Override
    public ResponseEntity<Student> updateStudent(Long idStudent, Student studentDetails) {
        Student updateStudent = studentRepository.findById(idStudent).get();

        updateStudent.setFirstName(studentDetails.getFirstName());
        updateStudent.setLastName(studentDetails.getLastName());
        updateStudent.setInternee(studentDetails.getInternee());

        studentRepository.save(updateStudent);
        return ResponseEntity.ok(updateStudent);
    }

    //----------------------GET----------------------
    @Override
    public List<Student> getAllStudents() {

        return studentRepository.findAll();
    }


    @Override
    public Student getStudentById(Long idStudent) throws StudentNotFound{
        return studentRepository.findById(idStudent)
                .orElseThrow(()-> new StudentNotFound("Student with Id : "+idStudent+" Not Found. "));
    }



    public List<Skill> getStudentSkills(Long idStudent) {
        Student student = studentRepository.findById(idStudent).orElse(null);
        if (student != null) {
            return new ArrayList<>(student.getSkills());
        }else {
            throw new StudentNotFound("Student with Id : " + idStudent + " Not Found. ");
        }
    }

    public List<Education> getStudentEducations(Long idStudent) {
        Student student = studentRepository.findById(idStudent).orElse(null);
        if (student != null) {
            return student.getEducations();
        }else {
            throw new StudentNotFound("Student with id : " + idStudent + " Not Found. ");
        }
    }

    public List<Certificate> getStudentCertificates(Long idStudent) {
        Student student = studentRepository.findById(idStudent).orElse(null);
        if (student != null) {
            return student.getCertificates();
        }else {
            throw new StudentNotFound("Student with id : " + idStudent + " Not Found. ");
        }
    }

    public List<Language> getStudentLanguages(Long idStudent) {
        Student student = studentRepository.findById(idStudent).orElse(null);
        if (student != null) {
            return student.getLanguages();
        }else {
            throw new StudentNotFound("Student with id : " + idStudent + " Not Found. ");
        }
    }

    public Location getStudentLocation(Long idStudent) {
        Student student = studentRepository.findById(idStudent).orElse(null);
        if (student != null) {
            return student.getLocation();
        }else {
            throw new StudentNotFound("Student with id : " + idStudent + " Not Found. ");
        }
    }


    public List<Contact> getStudentContacts(Long idStudent) {
        Student student = studentRepository.findById(idStudent).orElse(null);
        if (student != null) {
            return student.getContacts();
        }else {
            throw new StudentNotFound("Student with id : " + idStudent + " Not Found. ");
        }
    }

    @Override
    public List<Experience> getStudentExperiences(Long idStudent) {
        Student student = studentRepository.findById(idStudent).orElse(null);
        if (student != null) {
            return student.getExperiences();
        }else {
            throw new StudentNotFound("Student with id : " + idStudent + " Not Found. ");
        }
    }

    //----------------------DELETE----------------------
    @Override
    public void deleteStudent(Long idStudent) {
        try{
            studentRepository.deleteById(idStudent);}
        catch (Exception exception){
            System.out.println("Student doesn't exist");
        }
    }

    //... Verification
    @Override
    public boolean existsStudent(Long idStudent) {
        return studentRepository.existsById(idStudent);
    }


}
