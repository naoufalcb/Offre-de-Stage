package com.example.controller;

import com.example.dto.SkillCertificateDTO;
import com.example.exeption.basic.ValidationException;
import com.example.service.SkillCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/skill-certificate")
public class SkillCertificateController {

    @Autowired
    private SkillCertificateService skillCertificateService;

    // Endpoint to add a skill to a certificate
    @PostMapping("/addSkillToCertificate")
    public ResponseEntity<String> addSkillToCertificate(@RequestBody SkillCertificateDTO skillCertificateDTO) {
        Long idSkill = skillCertificateDTO.getIdSkill();
        Long idCertificate = skillCertificateDTO.getIdCertificate();

        if (idSkill == null || idCertificate == null) {
            throw new ValidationException("idSkill and idCertificate cannot be Null.");
        }

        if (!skillCertificateService.existsCertificate(idCertificate) && !skillCertificateService.existsSkill(idSkill)) {
            throw new ValidationException("Skill with id " + idSkill + " and Certificate with id "+idCertificate+" Not Found.");
        }

        if (!skillCertificateService.existsSkill(idSkill)) {
            throw new ValidationException("Skill with id " + idSkill + " Not Found.");
        }

        if (!skillCertificateService.existsCertificate(idCertificate)) {
            throw new ValidationException("Certificate with id " + idCertificate + " Not Found.");
        }

        skillCertificateService.addSkillToCertificate(idSkill, idCertificate);
        String responseMessage = "Skill : " + idSkill + " is added to Certificate : " + idCertificate;
        return ResponseEntity.ok(responseMessage);
    }

    // Endpoint to remove a skill from a certificate
    @DeleteMapping("/removeSkillFromCertificate")//done
    public ResponseEntity<String> removeSkillFromCertificate(@RequestBody SkillCertificateDTO skillCertificateDTO) {
        Long idSkill = skillCertificateDTO.getIdSkill();
        Long idCertificate = skillCertificateDTO.getIdCertificate();

        if (idSkill == null || idCertificate == null) {
            throw new ValidationException("idSkill and idCertificate cannot be Null.");
        }

        if (!skillCertificateService.existsCertificate(idCertificate) && !skillCertificateService.existsSkill(idSkill)) {
            throw new ValidationException("Skill with id " + idSkill + " and Certificate with id "+idCertificate+" Not Found.");
        }

        if (!skillCertificateService.existsSkill(idSkill)) {
            throw new ValidationException("Skill with id " + idSkill + " Not Found.");
        }

        if (!skillCertificateService.existsCertificate(idCertificate)) {
            throw new ValidationException("Certificate with id " + idCertificate + " Not Found.");
        }

        skillCertificateService.removeSkillFromCertificate(idSkill, idCertificate);
        String responseMessage = "Skill : " + idSkill + " is removed from Certificate : " + idCertificate;
        return ResponseEntity.ok(responseMessage);
    }

}

