package com.example.service;

import com.example.entities.Experience;
import com.example.entities.Skill;
import com.example.entities.Student;
import com.example.repository.ExperienceRepository;
import com.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ExperienceServiceImp implements ExperienceService{
    @Autowired
    ExperienceRepository experienceRepository;
    @Autowired
    StudentRepository studentRepository;

    @Override
    public Experience saveExperience(Experience newExperience) {
        return experienceRepository.save(newExperience);
    }

    @Override
    public Experience getExperienceById(Long IdExperience) {
        return experienceRepository.findById(IdExperience).orElse(null);
    }

    @Override
    public List<Experience> getAllExperiences() {
        return experienceRepository.findAll();
    }

    @Override
    public void deleteExperience(Long IdExperience) {
        experienceRepository.deleteById(IdExperience);
    }


    //----------------------PUT----------------------
    @Override
    public ResponseEntity<Experience> updateExperience(Long idExperience, Experience experienceDetails){
        Experience updateExperience = experienceRepository.findById(idExperience).get();
        updateExperience.setExperienceTitle(experienceDetails.getExperienceTitle());
        updateExperience.setEmploymentType(experienceDetails.getEmploymentType());
        updateExperience.setCompanyName(experienceDetails.getCompanyName());
        updateExperience.setCompanyLocation(experienceDetails.getCompanyLocation());
        updateExperience.setLocationType(experienceDetails.getLocationType());
        updateExperience.setStartDateExperience(experienceDetails.getStartDateExperience());
        updateExperience.setEndDateExperience(experienceDetails.getEndDateExperience());


        experienceRepository.save(updateExperience);
        return ResponseEntity.ok(updateExperience);

    }

    // ... Methods for querying and filtering experiences based on different criteria

    @Override
    public List<Experience> getExperiencesByCompanyName(String companyName) {
        return experienceRepository.findByCompanyName(companyName);
    }

    @Override
    public List<Experience> getExperiencesByEmploymentType(String employmentType) {
        return experienceRepository.findByEmploymentType(employmentType);
    }

    @Override
    public List<Experience> getExperiencesByLocationType(String locationType) {
        return experienceRepository.findByLocationType(locationType);
    }

    @Override
    public List<Experience> getExperiencesByIdStudent(Long IdStudent) {
        return experienceRepository.findByIdStudent(IdStudent);
    }

    @Override
    public void addExperienceToStudent(Long idStudent, Experience newExperience) {
        Student student = studentRepository.findById(idStudent).orElse(null);
        if (student != null) {
            newExperience.setStudent(student);
            student.getExperiences().add(newExperience);

            studentRepository.save(student);
            experienceRepository.save(newExperience);
        }
    }

    public void deleteExperienceFromStudent(Student student, Experience experience) {
        // Check if the experience is associated with the student
        if (student.getExperiences().contains(experience)) {
            // Remove the experience from the student's list of experiences
            student.getExperiences().remove(experience);
            // Update the student in the database (optional, if you want to sync the change)
            studentRepository.save(student);

            // Delete the experience from the database
            experienceRepository.delete(experience);
        } else {
            throw new IllegalArgumentException("The provided experience does not belong to the given student.");
        }
    }

    //... Verification
    @Override
    public boolean existsStudent(Long idStudent) {
        return studentRepository.existsById(idStudent);
    }

    @Override
    public boolean existsExperience(Long idExperience) {
        return experienceRepository.existsById(idExperience);
    }
}
