package com.example.controller;

import com.example.dto.SkillEducationDTO;
import com.example.dto.SkillEducationDTO;
import com.example.exeption.basic.ValidationException;
import com.example.service.SkillEducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/skill-education")
public class SkillEducationController {

    @Autowired
    private SkillEducationService skillEducationService;

    // Endpoint to add a skill to an education
    @PostMapping("/addSkillToEducation")
    public ResponseEntity<String> addSkillToEducation(@RequestBody SkillEducationDTO skillEducationDTO) {
        Long idSkill = skillEducationDTO.getIdSkill();
        Long idEducation = skillEducationDTO.getIdEducation();

        if (idSkill == null || idEducation == null) {
            throw new ValidationException("idSkill and idEducation cannot be Null.");
        }

        if (!skillEducationService.existsEducation(idEducation) && !skillEducationService.existsSkill(idSkill)) {
            throw new ValidationException("Skill with id " + idSkill + " and Education with id "+idEducation+" Not Found.");
        }

        if (!skillEducationService.existsSkill(idSkill)) {
            throw new ValidationException("Skill with id " + idSkill + " Not Found.");
        }

        if (!skillEducationService.existsEducation(idEducation)) {
            throw new ValidationException("Education with id " + idEducation + " Not Found.");
        }

        skillEducationService.addEducationToSkill(idSkill, idEducation);
        String responseMessage = "Skill : " + idSkill + " is added to Education : " + idEducation;
        return ResponseEntity.ok(responseMessage);
    }

    // Endpoint to remove a skill from a education
    @DeleteMapping("/removeSkillFromEducation")//done
    public ResponseEntity<String> removeSkillFromEducation(@RequestBody SkillEducationDTO skillEducationDTO) {
        Long idSkill = skillEducationDTO.getIdSkill();
        Long idEducation = skillEducationDTO.getIdEducation();

        if (idSkill == null || idEducation == null) {
            throw new ValidationException("idSkill and idEducation cannot be Null.");
        }

        if (!skillEducationService.existsEducation(idEducation) && !skillEducationService.existsSkill(idSkill)) {
            throw new ValidationException("Skill with id " + idSkill + " and Education with id "+idEducation+" Not Found.");
        }

        if (!skillEducationService.existsSkill(idSkill)) {
            throw new ValidationException("Skill with id " + idSkill + " Not Found.");
        }

        if (!skillEducationService.existsEducation(idEducation)) {
            throw new ValidationException("Education with id " + idEducation + " Not Found.");
        }

        skillEducationService.removeSkillFromEducation(idSkill, idEducation);
        String responseMessage = "Skill : " + idSkill + " is removed from Education : " + idEducation;
        return ResponseEntity.ok(responseMessage);
    }

}

