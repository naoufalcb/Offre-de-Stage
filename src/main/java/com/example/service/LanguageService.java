package com.example.service;

import com.example.entities.Experience;
import com.example.entities.Language;
import com.example.entities.Language;
import com.example.entities.Student;
import com.example.exeption.LanguageNotFound;
import com.example.exeption.StudentNotFound;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface LanguageService {
    //----------------------POST----------------------
    public Language saveLanguage(Language newLanguage);
    public void addLanguageToStudent(Long IdStudent,Long idLanguage);

    //----------------------PUT----------------------
    public ResponseEntity<Language> updateLanguage(Long idLanguage, Language languageDetails);

    //----------------------GET----------------------
    public List<Language> getAllLanguages();
    public Language getLanguageById(Long idLanguage) throws LanguageNotFound;
    public List<Student> getLanguageStudents(Long idLanguage) throws LanguageNotFound;

    //----------------------DELETE----------------------
    public void deleteLanguage(Long idLanguage);
    public void deleteLanguageToStudent(Long IdStudent,Long idLanguage);

    //... Verification
    boolean existsStudent(Long idStudent);

    boolean existsLanguage(Long idLanguage);
}
