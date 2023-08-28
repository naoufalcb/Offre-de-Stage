package com.example.service;

import com.example.entities.Certificate;
import com.example.entities.Education;
import com.example.entities.Skill;
import com.example.entities.Student;
import com.example.exeption.SkillNameAlreadyExist;
import com.example.exeption.SkillNotFound;
import com.example.repository.SkillRepository;
import com.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkillServiceImp implements SkillService{
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    SkillRepository skillRepository;

    //----------------------POST----------------------
    @Override
    public Skill save(Skill skill) throws SkillNameAlreadyExist {
        Skill s = skillRepository.findBySkillName(skill.getSkillName());
        if(s == null)
            return skillRepository.save(skill);
        throw new SkillNameAlreadyExist("The Skill name : "+s.getSkillName()+" Already exist");
    }

    @Override
    public void addSkillToStudent(Long idStudent, Long idSkill) {
        Student student = studentRepository.findById(idStudent).get();
        Skill skill = skillRepository.findById(idSkill).get();
        student.getSkills().add(skill);
        studentRepository.save(student);
    }

    //----------------------PUT----------------------
    @Override
    public ResponseEntity<Skill> updateSkill(Long idSkill, Skill skillDetails){
        Skill updateSkill = skillRepository.findById(idSkill).get();
            updateSkill.setSkillName(skillDetails.getSkillName());

            skillRepository.save(updateSkill);
            return ResponseEntity.ok(updateSkill);

    }

    //----------------------GET----------------------

    @Override
    public List<Skill> getAllSkills() {

        return skillRepository.findAll();
    }

    @Override
    public Skill getSkillById(Long idSkill) throws SkillNotFound{
        return skillRepository.findById(idSkill)
                .orElseThrow(()-> new SkillNotFound("Skill with id : "+idSkill+" Not Found. "));
    }

    public List<Student> getSkillStudents(Long idSkill) throws SkillNotFound{
        Skill skill = skillRepository.findById(idSkill).orElse(null);
        if (skill != null) {
            return skill.getStudents();
        }else {
            throw new SkillNotFound("Skill with id : " + idSkill + " Not Found. ");
        }
    }

    public List<Education> getSkillEducations(Long idSkill) throws SkillNotFound{
        Skill skill = skillRepository.findById(idSkill).orElse(null);
        if (skill != null) {
            return new ArrayList<>(skill.getEducations());
        }else {
            throw new SkillNotFound("Skill with id : " + idSkill + " Not Found. ");
        }
    }

    public List<Certificate> getSkillCertificates(Long idSkill)  throws SkillNotFound{
        Skill skill = skillRepository.findById(idSkill).orElse(null);
        if (skill != null) {
            return new ArrayList<>(skill.getCertificates());
        }else {
            throw new SkillNotFound("Skill with id : " + idSkill + " Not Found.");
        }
    }

    //----------------------DELETE----------------------
    @Override
    public void deleteSkill(Long idSkill) throws SkillNotFound{
        if (idSkill != null) {
            skillRepository.deleteById(idSkill);
        }else {
            throw new SkillNotFound("Skill Not Found.");
        }
    }

    @Override
    public void deleteSkillToStudent(Long idStudent, Long idSkill) {

        Student student = studentRepository.findById(idStudent).get();
        Skill skill = skillRepository.findById(idSkill).get();
        student.getSkills().remove(skill);
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
    public boolean existsSkill(Long idSkill) {
        // Implement the logic to check if the language with the given idLanguage exists in the database
        // You can use a repository or any other mechanism to perform the existence check
        // Return true if the language exists, false otherwise
        // For example:
        return skillRepository.existsById(idSkill);
    }

}
