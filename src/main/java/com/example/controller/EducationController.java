package com.example.controller;

import com.example.dto.StudentEducationDTO;
import com.example.entities.Education;
import com.example.entities.Skill;
import com.example.entities.Student;
import com.example.exeption.EducationNotFound;
import com.example.exeption.StudentNotFound;
import com.example.exeption.basic.ForbiddenException;
import com.example.exeption.basic.*;
import com.example.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/educations")
public class EducationController {

    @Autowired
    EducationService educationService;


    //----------------------POST----------------------
    @PostMapping("/create_education")//done
    public Education createEducation(@RequestBody Education newEducation) {

        return educationService.createEducation(newEducation);
    }

    /*@PostMapping("/save_education")

    public Education saveEducation(@RequestBody Education newEducation) throws EducationNameAlreadyExist {

        return educationService.saveEducation(newEducation);
    }*/

    @PostMapping("/addEducationToStudent")
    public ResponseEntity<String> addEducationToStudent(@RequestBody StudentEducationDTO studentEducationDTO) {
        Long idStudent = studentEducationDTO.getIdStudent();
        Long idEducation = studentEducationDTO.getIdEducation();

        if (idStudent == null || idEducation == null) {
            throw new ValidationException("idStudent and idEducation cannot be Null.");
        }

        if (!educationService.existsEducation(idEducation) && !educationService.existsStudent(idStudent)) {
            throw new ValidationException("Student with id " + idStudent + " and Education with id "+idEducation+" Not Found.");
        }

        if (!educationService.existsStudent(idStudent)) {
            throw new ValidationException("Student with id " + idStudent + " Not Found.");
        }

        if (!educationService.existsEducation(idEducation)) {
            throw new ValidationException("Education with id " + idEducation + " Not Found.");
        }

        educationService.addEducationToStudent(idStudent, idEducation);
        String responseMessage = "Education : " + idEducation + " is added to Student : " + idStudent;
        return ResponseEntity.ok(responseMessage);
    }
    //----------------------PUT----------------------

    @PutMapping("/edit_education")//done
    public String updateEducation(@RequestParam Long idEducation,
                              @RequestBody Education educationDetails) throws EducationNotFound {
        if (!educationService.existsEducation(idEducation)) {
            throw new EducationNotFound("Education with id " + idEducation + " Not Found.");
        }
        educationService.updateEducation(idEducation,educationDetails);
        return "Update of Education : "+idEducation+" Completed";
    }

    //----------------------GET----------------------
    @GetMapping("/find_all_education")//done
    public ResponseEntity<List<Education>> educationList(){
        List<Education> educations = educationService.getAllEducations();

        if (!educations.isEmpty()) {
            return ResponseEntity.ok(educations);
        } else {
            throw new EducationNotFound("No Education Found. ");
        }
    }

    @GetMapping("/find_education")//done
    public Education EducationById(@RequestParam("idEducation") Long idEducation){

        return educationService.getEducationById(idEducation);

    }

    @GetMapping("/{idEducation}/students")//done
    public ResponseEntity<List<Student>> getEducationStudents(@PathVariable Long idEducation) {
        List<Student> students = educationService.getEducationStudents(idEducation);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{idEducation}/skills")//done
    public ResponseEntity<List<Skill>> getEducationSkills(@PathVariable Long idEducation) {
        List<Skill> skills = educationService.getEducationSkills(idEducation);
        return ResponseEntity.ok(skills);
    }
    //----------------------DELETE----------------------
    @DeleteMapping("/{idEducation}")//done
    public ResponseEntity<String> deleteEducation(@PathVariable Long idEducation) throws EducationNotFound {
        Education education = educationService.getEducationById(idEducation);
        if (education != null) {
            educationService.deleteEducation(idEducation);
            return ResponseEntity.ok("Education "+idEducation+" deleted successfully");
        } else {
            throw new EducationNotFound("Education with Id : " + idEducation + " Not Found. ");
        }
    }

    @DeleteMapping("/deleteEducationToStudent")//done
    public ResponseEntity<String> deleteEducationToStudent(@RequestBody StudentEducationDTO studentEducationDTO){
        Long idStudent = studentEducationDTO.getIdStudent();
        Long idEducation = studentEducationDTO.getIdEducation();

        if (idStudent == null || idEducation == null) {
            throw new ValidationException("idStudent and idEducation cannot be Null.");
        }

        if (!educationService.existsEducation(idEducation) && !educationService.existsStudent(idStudent)) {
            throw new ValidationException("Student with id " + idStudent + " and Education with id "+idEducation+" Not Found.");
        }

        if (!educationService.existsStudent(idStudent)) {
            throw new ValidationException("Student with id " + idStudent + " Not Found.");
        }

        if (!educationService.existsEducation(idEducation)) {
            throw new ValidationException("Education with id " + idEducation + " Not Found.");
        }
        educationService.deleteEducationToStudent(idStudent, idEducation);
        String responseMessage = "Education : " + idEducation + " is deleted to Student : " + idStudent;
        return ResponseEntity.ok(responseMessage);
    }

    @GetMapping("/students/school")
    public ResponseEntity<List<Student>> getStudentsBySchool(@RequestBody String school) {
        if (!educationService.existsSchool(school)) {
            throw new EducationNotFound("No School Found with name : "+school);
        } else {
            List<Student> students = educationService.getStudentsBySchool(school);
            return ResponseEntity.ok(students);
        }
    }

    @GetMapping("/students/regulatedAccess/{status}")
    public ResponseEntity<List<Student>> getStudentsByRegulatedAccess(@PathVariable boolean status) {
            List<Student> students = educationService.getStudentsByRegulatedAccess(status);
            if (students.isEmpty()) {
                return ResponseEntity.ok(students);
            } else {
                throw new StudentNotFound("No Student Found with status: " + status);
            }
    }

    @GetMapping("/students/fieldOfStudy")
    public List<Student> getStudentsByFieldOfStudy(@RequestBody String fieldOfStudy) {
        return educationService.getStudentsByFieldOfStudy(fieldOfStudy);
    }
}
