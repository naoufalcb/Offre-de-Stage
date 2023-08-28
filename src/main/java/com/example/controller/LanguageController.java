package com.example.controller;

import com.example.dto.StudentLanguageDTO;
import com.example.entities.Language;
import com.example.entities.Language;
import com.example.entities.Student;
import com.example.exeption.LanguageNotFound;
import com.example.exeption.LanguageNotFound;
import com.example.exeption.StudentNotFound;
import com.example.exeption.basic.ValidationException;
import com.example.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/languages")
public class LanguageController {

    @Autowired
    LanguageService languageService;


    //----------------------POST----------------------
    @PostMapping("/createLanguage")//done
    public Language createLanguage(@RequestBody Language newLanguage) {

        return languageService.saveLanguage(newLanguage);
    }

    @PostMapping("/addLanguageToStudent")
    public ResponseEntity<String> addLanguageToStudent(@RequestBody StudentLanguageDTO studentLanguageDTO) {
        Long idStudent = studentLanguageDTO.getIdStudent();
        Long idLanguage = studentLanguageDTO.getIdLanguage();

        if (idStudent == null || idLanguage == null) {
            throw new ValidationException("idStudent and idLanguage cannot be Null.");
        }

        if (!languageService.existsLanguage(idLanguage) && !languageService.existsStudent(idStudent)) {
            throw new ValidationException("Student with id " + idStudent + " and Language with id "+idLanguage+" Not Found.");
        }

        if (!languageService.existsStudent(idStudent)) {
            throw new ValidationException("Student with id " + idStudent + " Not Found.");
        }

        if (!languageService.existsLanguage(idLanguage)) {
            throw new ValidationException("Language with id " + idLanguage + " Not Found.");
        }

        languageService.addLanguageToStudent(idStudent, idLanguage);
        String responseMessage = "Language : " + idLanguage + " is added to Student : " + idStudent;
        return ResponseEntity.ok(responseMessage);
    }

    //----------------------PUT----------------------
    @PutMapping("/edit_language")//done
    public String updateLanguage(@RequestParam Long idLanguage,
                                 @RequestBody Language languageDetails){
        if (!languageService.existsLanguage(idLanguage)) {
            throw new LanguageNotFound("Language with id " + idLanguage + " not found.");
        }
        languageService.updateLanguage(idLanguage,languageDetails);
        return "Update of Language : "+idLanguage+" Completed";
    }
    //----------------------GET----------------------
    @GetMapping("/getAllLanguages")//done//Ex Works
    public ResponseEntity<List<Language>> getAllLanguages() throws LanguageNotFound{

        List<Language> languages= languageService.getAllLanguages();
        if (!languages.isEmpty()) {
            return ResponseEntity.ok(languages);
        } else {
            throw new LanguageNotFound("No Language Found. ");
        }

    }

    @GetMapping("/{idLanguage}")//done//Ex Works
    public ResponseEntity<Language> getLanguageById(@PathVariable Long idLanguage) throws LanguageNotFound {
        Language language = languageService.getLanguageById(idLanguage);
        if (language != null) {
            return ResponseEntity.ok(language);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{idLanguage}/students")//done//Ex Works
    public ResponseEntity<List<Student>> getLanguageStudents(@PathVariable Long idLanguage) throws LanguageNotFound{
        List<Student> students = languageService.getLanguageStudents(idLanguage);
        return ResponseEntity.ok(students);
    }

    //----------------------DELETE----------------------
    @DeleteMapping("/{idLanguage}")//done
    public ResponseEntity<String> deleteLanguage(@PathVariable Long idLanguage) throws LanguageNotFound {
        Language language = languageService.getLanguageById(idLanguage);
        if (language != null) {
            languageService.deleteLanguage(idLanguage);
            return ResponseEntity.ok("Language "+idLanguage+" deleted successfully");
        } else {
            throw new LanguageNotFound("Language with Id : " + idLanguage + " Not Found. ");
        }
    }

    @DeleteMapping("/deleteLanguageToStudent")//done
    public ResponseEntity<String> deleteLanguageToStudent(@RequestBody StudentLanguageDTO studentLanguageDTO){
        Long idStudent = studentLanguageDTO.getIdStudent();
        Long idLanguage = studentLanguageDTO.getIdLanguage();

        if (idStudent == null || idLanguage == null) {
            throw new ValidationException("idStudent and idLanguage cannot be null.");
        }

        if (!languageService.existsLanguage(idLanguage) && !languageService.existsStudent(idStudent)) {
            throw new ValidationException("Student with id " + idStudent + " and Language withq  id "+idLanguage+" not found.");
        }

        if (!languageService.existsStudent(idStudent)) {
            throw new ValidationException("Student with id " + idStudent + " not found.");
        }

        if (!languageService.existsLanguage(idLanguage)) {
            throw new ValidationException("Language with id " + idLanguage + " not found.");
        }
        languageService.deleteLanguageToStudent(studentLanguageDTO.getIdStudent(),studentLanguageDTO.getIdLanguage());
        String responseMessage = "Language : " + idLanguage + " is added to Student : " + idStudent;
        return ResponseEntity.ok(responseMessage);
    }

}
