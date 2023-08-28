package com.example.service;

import com.example.entities.Language;
import com.example.entities.Language;
import com.example.entities.Language;
import com.example.entities.Student;
import com.example.exeption.LanguageNotFound;
import com.example.exeption.StudentNotFound;
import com.example.repository.LanguageRepository;
import com.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class LanguageServiceImp implements LanguageService{
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    LanguageRepository languageRepository;

    //----------------------POST----------------------
    @Override
    public Language saveLanguage(Language newLanguage) {

        return languageRepository.save(newLanguage);
    }

    @Override
    public void addLanguageToStudent(Long IdStudent, Long idLanguage) {
        Student student = studentRepository.findById(IdStudent).get();
        Language language = languageRepository.findById(idLanguage).get();
        student.getLanguages().add(language);
        studentRepository.save(student);
    }
    //----------------------PUT----------------------
    @Override
    public ResponseEntity<Language> updateLanguage(Long idLanguage, Language languageDetails) {
        Language updateLanguage = languageRepository.findById(idLanguage).get();

        updateLanguage.setLanguageName(languageDetails.getLanguageName());
        updateLanguage.setLanguageLevel(languageDetails.getLanguageLevel());
        updateLanguage.setLanguageSource(languageDetails.getLanguageSource());

        languageRepository.save(updateLanguage);
        return ResponseEntity.ok(updateLanguage);
    }


    //----------------------GET----------------------
    @Override
    public List<Language> getAllLanguages() {

        return languageRepository.findAll();
    }

    @Override
    public Language getLanguageById(Long idLanguage) {
        return languageRepository.findById(idLanguage)
                .orElseThrow(()-> new LanguageNotFound("Language with Id : "+idLanguage+" Not Found. "));
    }

    public List<Student> getLanguageStudents(Long idLanguage) {
        Language language = languageRepository.findById(idLanguage).orElse(null);
        if (language != null) {
            return language.getStudents();
        }else {
            throw new LanguageNotFound("Language with Id : " + idLanguage + " Not Found. ");
        }
    }

    //----------------------DELETE----------------------
    @Override
    public void deleteLanguage(Long idLanguage) {

        languageRepository.deleteById(idLanguage);
    }

    @Override
    public void deleteLanguageToStudent(Long IdStudent, Long idLanguage) {

        Student student = studentRepository.findById(IdStudent).get();
        Language language = languageRepository.findById(idLanguage).get();
        student.getLanguages().remove(language);
        studentRepository.save(student);
    }

    //... Verification
    @Override
    public boolean existsStudent(Long idStudent) {
        // Implement the logic to check if the student with the given idStudent exists in the database
        // You can use a repository or any other mechanism to perform the existence check
        // Return true if the student exists, false otherwise
        // For example:
         return studentRepository.existsById(idStudent);
    }

    @Override
    public boolean existsLanguage(Long idLanguage) {
        // Implement the logic to check if the language with the given idLanguage exists in the database
        // You can use a repository or any other mechanism to perform the existence check
        // Return true if the language exists, false otherwise
        // For example:
         return languageRepository.existsById(idLanguage);
    }


}
