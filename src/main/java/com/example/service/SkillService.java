package com.example.service;

import com.example.entities.Certificate;
import com.example.entities.Education;
import com.example.entities.Skill;
import com.example.entities.Student;
import com.example.exeption.SkillNameAlreadyExist;
import com.example.exeption.SkillNotFound;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SkillService {
    //----------------------POST----------------------
    public Skill save(Skill skill) throws SkillNameAlreadyExist;
    public void addSkillToStudent(Long idStudent,Long idSkill);
    //----------------------PUT----------------------
    public ResponseEntity<Skill> updateSkill(Long idSkill, Skill skillDetails);
    //----------------------GET----------------------
    public List<Skill> getAllSkills();
    public Skill getSkillById(Long idSkill) throws SkillNotFound;
    public List<Student> getSkillStudents(Long idSkill) throws SkillNotFound;
    public List<Education> getSkillEducations(Long idSkill) throws SkillNotFound;
    public List<Certificate> getSkillCertificates(Long idSkill) throws SkillNotFound;
    //----------------------DELETE----------------------
    public void deleteSkill(Long idSkill) throws SkillNotFound;//Not Working
    public void deleteSkillToStudent(Long idStudent,Long idSkill);

    //... Verification
    boolean existsStudent(Long idStudent);

    boolean existsSkill(Long idSkill);

}
