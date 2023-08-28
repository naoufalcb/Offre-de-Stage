package com.example.service;

import com.example.entities.Education;
import com.example.entities.Skill;
import com.example.entities.Student;
import com.example.exeption.EducationNotFound;
import com.example.exeption.StudentNotFound;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EducationService {
    //----------------------POST----------------------
    public Education createEducation(Education newEducation);
    /*public Education saveEducation(Education education) throws EducationNameAlreadyExist;*/
    public void addEducationToStudent(Long idStudent,Long idEducation);

    //----------------------PUT----------------------
    public ResponseEntity<Education> updateEducation(Long idEducation, Education educationDetails);

    //----------------------GET----------------------
    public List<Education> getAllEducations();
    public Education getEducationById(Long idEducation) throws EducationNotFound;
    public List<Student> getEducationStudents(Long idEducation) throws EducationNotFound;
    public List<Skill> getEducationSkills(Long idEducation) throws EducationNotFound;


    //----------------------DELETE----------------------
    public void deleteEducation(Long idEducation);
    public void deleteEducationToStudent(Long idStudent,Long idEducation);


    public List<Student> getStudentsBySchool(String school);
    public List<Student> getStudentsByRegulatedAccess(Boolean regulatedAccess);
    public List<Student> getStudentsByFieldOfStudy(String fieldOfStudy);

    //... Verification
    boolean existsStudent(Long idStudent);
    boolean existsEducation(Long idEducation);
    boolean existsSchool(String school);
}
