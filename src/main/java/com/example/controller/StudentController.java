package com.example.controller;

import com.example.entities.*;
import com.example.exeption.ContactNotFound;
import com.example.exeption.LanguageNotFound;
import com.example.exeption.StudentNotFound;
import com.example.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentService studentService;

    //----------------------POST----------------------
    @PostMapping("/create_student")//done
    public Student createStudent(@RequestBody Student newStudent) {

        return studentService.createStudent(newStudent);
    }

    //----------------------PUT----------------------

    @PutMapping("/edit_student")
    //@PreAuthorize("hasAnyAuthority('STUDENT','ADMIN')")
    @PreAuthorize("hasAuthority('STUDENT')")
    public String updateStudent(@RequestParam Long idStudent,
                                 @RequestBody Student studentDetails){
        if (!studentService.existsStudent(idStudent)) {
            throw new StudentNotFound("Student with id " + idStudent + " Not Found.");
        }
        studentService.updateStudent(idStudent,studentDetails);
        return "Update of Student : "+idStudent+" Completed";
    }

    //----------------------GET----------------------

    @GetMapping("/getAllStudents")//done
    @PreAuthorize("hasAuthority('STUDENT')")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        if (!students.isEmpty()) {
            return ResponseEntity.ok(students);
        } else {
            throw new StudentNotFound("No Student Found. ");
        }
    }

    @GetMapping("/{idStudent}")//done
    //@PreAuthorize("hasAuthority('STUDENT')")
    @PreAuthorize("hasAnyAuthority('STUDENT','ADMIN')")
    public ResponseEntity<Student> getStudentById(@PathVariable Long idStudent) {
        Student student = studentService.getStudentById(idStudent);
        if (student != null) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{idStudent}/skills")//done
    @PreAuthorize("hasAnyAuthority('STUDENT','ADMIN')")
    public ResponseEntity<List<Skill>> getStudentSkills(@PathVariable Long idStudent) {
        List<Skill> skills = studentService.getStudentSkills(idStudent);
        return ResponseEntity.ok(skills);
    }

    @GetMapping("/{idStudent}/educations")//done
    @PreAuthorize("hasAnyAuthority('STUDENT','ADMIN')")
    public ResponseEntity<List<Education>> getStudentEducations(@PathVariable Long idStudent) {
        List<Education> educations = studentService.getStudentEducations(idStudent);
        return ResponseEntity.ok(educations);
    }

    @GetMapping("/{idStudent}/certificates")
    @PreAuthorize("hasAnyAuthority('STUDENT','ADMIN')")
    public ResponseEntity<List<Certificate>> getStudentCertificates(@PathVariable Long idStudent) {
        List<Certificate> certificates = studentService.getStudentCertificates(idStudent);
        return ResponseEntity.ok(certificates);
    }

    @GetMapping("/{idStudent}/languages")
    @PreAuthorize("hasAnyAuthority('STUDENT','ADMIN')")
    public ResponseEntity<List<Language>> getStudentLanguages(@PathVariable Long idStudent) {
        List<Language> languages = studentService.getStudentLanguages(idStudent);
        return ResponseEntity.ok(languages);
    }

    @GetMapping("/{idStudent}/location")
    @PreAuthorize("hasAnyAuthority('STUDENT','ADMIN')")
    public ResponseEntity<Location> getStudentLocation(@PathVariable Long idStudent) {
        Location location = studentService.getStudentLocation(idStudent);
        return ResponseEntity.ok(location);
    }

    @GetMapping("/{idStudent}/contacts")
    @PreAuthorize("hasAnyAuthority('STUDENT','ADMIN')")
    public ResponseEntity<List<Contact>> getStudentContacts(@PathVariable Long idStudent) {
        List<Contact> contacts = studentService.getStudentContacts(idStudent);
        return ResponseEntity.ok(contacts);
    }

    @GetMapping("/{idStudent}/experiences")
    @PreAuthorize("hasAnyAuthority('STUDENT','ADMIN')")
    public ResponseEntity<List<Experience>> getExperiencesByStudentId(@PathVariable Long idStudent) {
        List<Experience> experiences = studentService.getStudentExperiences(idStudent);

            return ResponseEntity.ok(experiences);
    }

    //----------------------DELETE----------------------
    @DeleteMapping("/{idStudent}")//done
    @PreAuthorize("hasAnyAuthority('STUDENT','ADMIN')")
    public ResponseEntity<String> deleteStudent(@PathVariable Long idStudent){
        Student student = studentService.getStudentById(idStudent);
        if (student != null) {
            studentService.deleteStudent(idStudent);
            return ResponseEntity.ok("Student "+idStudent+" deleted successfully");
        } else {
            throw new StudentNotFound("Student with Id : " + idStudent + " Not Found. ");
        }
    }


}
