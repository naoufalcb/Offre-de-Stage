package com.example.controller;

import com.example.dto.StudentSkillDTO;
import com.example.dto.StudentSkillDTO;
import com.example.entities.Certificate;
import com.example.entities.Education;
import com.example.entities.Skill;
import com.example.entities.Student;
import com.example.exeption.SkillNameAlreadyExist;
import com.example.exeption.SkillNotFound;
import com.example.exeption.StudentNotFound;
import com.example.exeption.basic.ValidationException;
import com.example.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skills")
public class SkillController {

    @Autowired
    SkillService skillService;


    //----------------------POST----------------------
    @PostMapping("/save_skill")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Skill saveSkill(@RequestBody Skill newSkill) throws SkillNameAlreadyExist {
        return skillService.save(newSkill);
    }

    @PostMapping("/addSkillToStudent")
    @PreAuthorize("hasAnyAuthority('STUDENT','ADMIN')")
    public ResponseEntity<String> addSkillToStudent(@RequestBody StudentSkillDTO studentSkillDTO) {
        Long idStudent = studentSkillDTO.getIdStudent();
        Long idSkill = studentSkillDTO.getIdSkill();

        if (idStudent == null || idSkill == null) {
            throw new ValidationException("idStudent and idSkill cannot be Null.");
        }

        if (!skillService.existsSkill(idSkill) && !skillService.existsStudent(idStudent)) {
            throw new ValidationException("Student with id " + idStudent + " and Skill with id "+idSkill+" Not Found.");
        }

        if (!skillService.existsStudent(idStudent)) {
            throw new ValidationException("Student with id " + idStudent + " Not Found.");
        }

        if (!skillService.existsSkill(idSkill)) {
            throw new ValidationException("Skill with id " + idSkill + " Not Found.");
        }

        skillService.addSkillToStudent(idStudent, idSkill);
        String responseMessage = "Skill : " + idSkill + " is added to Student : " + idStudent;
        return ResponseEntity.ok(responseMessage);
    }

    //----------------------PUT----------------------
    @PutMapping("/edit_skill")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String updateSkill(@RequestParam Long idSkill,
                                @RequestBody Skill skillDetails) throws SkillNotFound {
        if (!skillService.existsSkill(idSkill)) {
            throw new SkillNotFound("Skill with id " + idSkill + " Not Found.");
        }
        skillService.updateSkill(idSkill,skillDetails);
        return "Update of Skill : "+idSkill+" Completed";
    }

    //----------------------GET----------------------
    @GetMapping("/find_all_skill")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<Skill>> skillList() throws SkillNotFound {
        List<Skill> skills = skillService.getAllSkills();
        if (!skills.isEmpty()) {
        return ResponseEntity.ok(skills);
        } else {
            throw new SkillNotFound("No Skill Found. ");
        }
    }

    @GetMapping("/{idSkill}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Skill> getSkillById(@PathVariable Long idSkill) throws SkillNotFound {
        Skill skill = skillService.getSkillById(idSkill);
        if (skill != null) {
            return ResponseEntity.ok(skill);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{idSkill}/students")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<Student>> getSkillStudents(@PathVariable Long idSkill) throws SkillNotFound{
        List<Student> students = skillService.getSkillStudents(idSkill);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{idSkill}/educations")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<Education>> getSkillEducations(@PathVariable Long idSkill) throws SkillNotFound{
        List<Education> educations = skillService.getSkillEducations(idSkill);
        return ResponseEntity.ok(educations);
    }

    @GetMapping("/{idSkill}/certificates")//done
    public ResponseEntity<List<Certificate>> getSkillCertificates(@PathVariable Long idSkill) throws SkillNotFound{
        List<Certificate> certificates = skillService.getSkillCertificates(idSkill);
        return ResponseEntity.ok(certificates);
    }
    //----------------------DELETE----------------------
    /*@DeleteMapping("/deleteSkill")//done
    public void deleteSkill(@RequestParam("idSkill") Long idSkill) throws SkillNotFound{
        skillService.deleteSkill(idSkill);
    }*/

    @DeleteMapping("/{idSkill}")//done
    public ResponseEntity<String> deleteSkill(@PathVariable Long idSkill) throws SkillNotFound {
        Skill skill = skillService.getSkillById(idSkill);
        if (skill != null) {
            skillService.deleteSkill(idSkill);
            return ResponseEntity.ok("Skill "+idSkill+" deleted successfully");
        } else {
            throw new SkillNotFound("Skill with Id : " + idSkill + " Not Found. ");
        }
    }

    /*@DeleteMapping("/deleteSkillToStudent")
    public String deleteSkillToStudent(@RequestBody StudentSkillDTO studentSkillDTO){
        skillService.deleteSkillToStudent(studentSkillDTO.getIdStudent(),studentSkillDTO.getIdSkill());
        return  "Skill : "+studentSkillDTO.getIdSkill()+" is deleted to Student : "+studentSkillDTO.getIdStudent();
    }*/

    @DeleteMapping("/deleteSkillToStudent")
    public ResponseEntity<String> deleteSkillToStudent(@RequestBody StudentSkillDTO studentSkillDTO) {
        Long idStudent = studentSkillDTO.getIdStudent();
        Long idSkill = studentSkillDTO.getIdSkill();

        if (idStudent == null || idSkill == null) {
            throw new ValidationException("idStudent and idSkill cannot be Null.");
        }

        if (!skillService.existsSkill(idSkill) && !skillService.existsStudent(idStudent)) {
            throw new ValidationException("Student with id " + idStudent + " and Skill with id "+idSkill+" Not Found.");
        }

        if (!skillService.existsStudent(idStudent)) {
            throw new ValidationException("Student with id " + idStudent + " Not Found.");
        }

        if (!skillService.existsSkill(idSkill)) {
            throw new ValidationException("Skill with id " + idSkill + " Not Found.");
        }

        skillService.deleteSkillToStudent(idStudent, idSkill);
        String responseMessage = "Skill : " + idSkill + " is deleted to Student : " + idStudent;
        return ResponseEntity.ok(responseMessage);
    }

}
