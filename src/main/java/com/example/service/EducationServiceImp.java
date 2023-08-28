package com.example.service;

import com.example.entities.Education;
import com.example.entities.Skill;
import com.example.entities.Student;
import com.example.exeption.EducationNotFound;
import com.example.exeption.StudentNotFound;
import com.example.repository.EducationRepository;
import com.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EducationServiceImp implements EducationService{
    //----------------------POST----------------------
    @Autowired
    EducationRepository educationRepository;
    @Autowired
    StudentRepository studentRepository;

    @Override
    public Education createEducation(Education newEducation) {
        return educationRepository.save(newEducation);
    }

    /*@Override
    public Education saveEducation(Education education) throws EducationNameAlreadyExist {
        Education e = educationRepository.findBySchool(education.getSchool());
        if(e == null)
            return educationRepository.save(education);
        throw new EducationNameAlreadyExist("The School : "+e.getSchool()+" Already exist");
    }*/

    @Override
    public void addEducationToStudent(Long idStudent, Long idEducation) {
        Student student = studentRepository.findById(idStudent).get();
        Education education = educationRepository.findById(idEducation).get();
        student.getEducations().add(education);
        studentRepository.save(student);
    }

    //----------------------PUT----------------------
    @Override
    public ResponseEntity<Education> updateEducation(Long idEducation, Education educationDetails) {
        Education updateEducation = educationRepository.findById(idEducation).get();

        updateEducation.setSchool(educationDetails.getSchool());
        updateEducation.setRegulatedAccess(educationDetails.getRegulatedAccess());
        updateEducation.setFieldOfStudy(educationDetails.getFieldOfStudy());
        updateEducation.setFieldOfStudy(educationDetails.getFieldOfStudy());
        updateEducation.setGrade(educationDetails.getGrade());
        updateEducation.setActivities(educationDetails.getActivities());
        updateEducation.setEducationDescription(educationDetails.getEducationDescription());
        updateEducation.setStartDateEducation(educationDetails.getStartDateEducation());
        updateEducation.setFinishDateEducation(educationDetails.getFinishDateEducation());


        educationRepository.save(updateEducation);
        return ResponseEntity.ok(updateEducation);
    }

    //----------------------GET----------------------
    @Override
    public List<Education> getAllEducations() {

        return educationRepository.findAll();
    }


    @Override
    public Education getEducationById(Long idEducation) {
        return educationRepository.findById(idEducation)
                .orElseThrow(()-> new EducationNotFound("Education with Id : "+idEducation+" Not Found. "));
    }

    public List<Student> getEducationStudents(Long idEducation) {
        Education education = educationRepository.findById(idEducation).orElse(null);
        if (education != null) {
            return education.getStudents();
        }else {
            throw new EducationNotFound("Education with Id : " + idEducation + " Not Found. ");
        }
    }

    public List<Skill> getEducationSkills(Long idEducation) {
        Education education = educationRepository.findById(idEducation).orElse(null);
        if (education != null) {
            return new ArrayList<>(education.getSkills());
        }else {
            throw new EducationNotFound("Education with Id : " + idEducation + " Not Found. ");
        }
    }

    //----------------------DELETE----------------------
    @Override
    public void deleteEducation(Long idEducation) {
        educationRepository.deleteById(idEducation);
    }

    @Override
    public void deleteEducationToStudent(Long idStudent, Long idEducation) {

        Student student = studentRepository.findById(idStudent).get();
        Education education = educationRepository.findById(idEducation).get();
        student.getEducations().remove(education);
        studentRepository.save(student);
    }

    @Override
    public List<Student> getStudentsBySchool(String school) {
        return educationRepository.findBySchool(school).getStudents();
    }

    @Override
    public List<Student> getStudentsByRegulatedAccess(Boolean regulatedAccess) {
        return educationRepository.findByRegulatedAccess(regulatedAccess).getStudents();
    }

    @Override
    public List<Student> getStudentsByFieldOfStudy(String fieldOfStudy) {
        return educationRepository.findByFieldOfStudy(fieldOfStudy).getStudents();
    }

    //... Verification
    @Override
    public boolean existsStudent(Long idStudent) {
        return studentRepository.existsById(idStudent);
    }
    @Override
    public boolean existsEducation(Long idEducation) {
        return educationRepository.existsById(idEducation);
    }
    public boolean existsSchool(String school) {
        return educationRepository.existsBySchool(school);
    }

}
