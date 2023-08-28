package com.example.controller;

import com.example.entities.Experience;
import com.example.entities.Student;
import com.example.exeption.ExperienceNotFound;
import com.example.exeption.StudentNotFound;
import com.example.service.ExperienceService;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/experiences")
public class ExperienceController {

    @Autowired
    ExperienceService experienceService;
    @Autowired
    StudentService studentService;
    //----------------------POST----------------------
    @PostMapping
    public ResponseEntity<Experience> createExperience(@RequestBody Experience experience) {
        Experience savedExperience = experienceService.saveExperience(experience);
        return ResponseEntity.created(URI.create("/experiences/" + savedExperience.getIdExperience())).body(savedExperience);
    }
    //----------------------PUT----------------------
    /*@PutMapping("/{idExperience}")
    public ResponseEntity<Experience> updateExperience(@PathVariable Long idExperience, @RequestBody Experience updatedExperience) {
        Experience experience = experienceService.getExperienceById(idExperience);
        if (experience != null) {
            updatedExperience.setidExperience(idExperience);
            Experience savedExperience = experienceService.saveExperience(updatedExperience);
            return ResponseEntity.ok(savedExperience);
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/

    @PutMapping("/edit_experience")//done
    public String updateExperience(@RequestParam Long idExperience,
                                @RequestBody Experience experienceDetails){
        if (!experienceService.existsExperience(idExperience)) {
            /*return "Update of Experience : "+idExperience+" Completed";*/
            throw new ExperienceNotFound("Experience with id " + idExperience + " Not Found.");
        }
        experienceService.updateExperience(idExperience,experienceDetails);
        return "Update of Experience : "+idExperience+" Completed.";
    }
    //----------------------GET----------------------
    @GetMapping("/{idExperience}")
    public ResponseEntity<Experience> getExperienceById(@PathVariable Long idExperience) {
        Experience experience = experienceService.getExperienceById(idExperience);
        if (experience != null) {
            return ResponseEntity.ok(experience);
        } else {
            throw new ExperienceNotFound("Experience with Id : "+idExperience+" Not Found. ");
        }
    }

    @GetMapping
    public ResponseEntity<List<Experience>> getAllExperiences() throws ExperienceNotFound {
        List<Experience> experiences = experienceService.getAllExperiences();
        if (!experiences.isEmpty()) {
            return ResponseEntity.ok(experiences);
        } else {
            throw new ExperienceNotFound("No Experience Found. ");
        }
    }

    //----------------------DELETE----------------------
    @DeleteMapping("/{idExperience}")
    public ResponseEntity<String> deleteExperience(@PathVariable Long idExperience) {
        Experience experience = experienceService.getExperienceById(idExperience);
        if (experience != null) {
            experienceService.deleteExperience(idExperience);

            return ResponseEntity.ok("Experience "+idExperience+" deleted successfully");
        } else {
            throw new ExperienceNotFound("Experience Not Found. ");
        }
    }
    //...Other
    @GetMapping("/company/{companyName}")
    public ResponseEntity<List<Experience>> getExperiencesByCompanyName(@PathVariable String companyName) {
        List<Experience> experiences = experienceService.getExperiencesByCompanyName(companyName);
        if (!experiences.isEmpty()) {
            return ResponseEntity.ok(experiences);
        } else {
            throw new ExperienceNotFound("No Experience Found with Company Name : "+companyName+". ");
        }
    }

    @GetMapping("/employmentType/{employmentType}")
    public ResponseEntity<List<Experience>> getExperiencesByEmploymentType(@PathVariable String employmentType) {
        List<Experience> experiences = experienceService.getExperiencesByEmploymentType(employmentType);
        if (!experiences.isEmpty()) {
            return ResponseEntity.ok(experiences);
        } else {
            throw new ExperienceNotFound("No Experience Found with Employment Type : "+employmentType+" . ");
        }
    }

    @GetMapping("/locationType/{locationType}")
    public ResponseEntity<List<Experience>> getExperiencesByLocationType(@PathVariable String locationType) {
        List<Experience> experiences = experienceService.getExperiencesByLocationType(locationType);
        if (!experiences.isEmpty()) {
            return ResponseEntity.ok(experiences);
        } else {
            throw new ExperienceNotFound("No Experience Found with Location Type : "+locationType+" . ");
        }
    }

    @GetMapping("/student/{idStudent}")
    public ResponseEntity<List<Experience>> getExperiencesByIdStudent(@PathVariable Long idStudent) {
        List<Experience> experiences = experienceService.getExperiencesByIdStudent(idStudent);
        if (!studentService.existsStudent(idStudent)) {
            throw new StudentNotFound("Student with id " + idStudent + " Not Found.");
        } else if (!experiences.isEmpty()) {
            return ResponseEntity.ok(experiences);
        } else {
            throw new ExperienceNotFound("No Experience Found for Student : "+idStudent+". ");
        }
    }
    @PostMapping("/student/{idStudent}/experience")
    public ResponseEntity<String> addExperienceToStudent(@PathVariable Long idStudent, @RequestBody Experience newExperience) {
            if (!studentService.existsStudent(idStudent)) {
                throw new StudentNotFound("Student with id " + idStudent + " Not Found.");
            }else {
            experienceService.addExperienceToStudent(idStudent, newExperience);
            return ResponseEntity.ok("Experience added to student "+idStudent+" successfully.");}
    }

    @DeleteMapping("/student/{idStudent}/experience/{idExperience}")
    public ResponseEntity<String> removeExperienceFromStudent(
            @PathVariable Long idStudent,
            @PathVariable Long idExperience
    ) {
        Student student = studentService.getStudentById(idStudent);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }

        Experience experience = student.getExperiences().stream()
                .filter(c -> c.getIdExperience().equals(idExperience))
                .findFirst()
                .orElse(null);

        if (experience == null) {
            return ResponseEntity.notFound().build();
        }

        experienceService.deleteExperienceFromStudent(student, experience);
        return ResponseEntity.ok("Experience with ID " + idExperience + " removed from the student " + idStudent + " .");
    }
}
