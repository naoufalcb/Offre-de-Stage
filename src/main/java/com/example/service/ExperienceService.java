package com.example.service;

import com.example.entities.Experience;
import com.example.entities.Student;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ExperienceService {
    public Experience saveExperience(Experience newExperience);
    public Experience getExperienceById(Long IdExperience);
    public List<Experience> getAllExperiences();
    public void deleteExperience(Long IdExperience);

    //----------------------PUT----------------------
    public ResponseEntity<Experience> updateExperience(Long idExperience, Experience experienceDetails);

    // ... Methods for querying and filtering experiences based on different criteria

    public List<Experience> getExperiencesByCompanyName(String companyName);
    public List<Experience> getExperiencesByEmploymentType(String employmentType);
    public List<Experience> getExperiencesByLocationType(String locationType);
    public List<Experience> getExperiencesByIdStudent(Long IdStudent);

    public void addExperienceToStudent(Long idStudent, Experience newExperience);
    public void deleteExperienceFromStudent(Student student, Experience experience);

    //... Verification
    boolean existsStudent(Long idStudent);

    boolean existsExperience(Long idExperience);
}
