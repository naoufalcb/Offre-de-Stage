package com.example.service;

import com.example.entities.Certificate;
import com.example.entities.Skill;
import com.example.repository.CertificateRepository;
import com.example.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillCertificateServiceImp implements SkillCertificateService{
    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private CertificateRepository certificateRepository;

    @Override
    public void addSkillToCertificate(Long idSkill, Long idCertificate) {
        Skill skill = skillRepository.findById(idSkill).orElse(null);
        Certificate certificate = certificateRepository.findById(idCertificate).orElse(null);

        if (skill != null && certificate != null) {
            skill.addCertificate(certificate);
            skillRepository.save(skill);
        }
    }

    @Override
    public void removeSkillFromCertificate(Long idSkill, Long idCertificate) {
        Skill skill = skillRepository.findById(idSkill).orElse(null);
        Certificate certificate = certificateRepository.findById(idCertificate).orElse(null);

        if (skill != null && certificate != null) {
            skill.removeCertificate(certificate);
            skillRepository.save(skill);
        }
    }

    @Override
    public void addCertificateToSkill(Long idCertificate, Long idSkill) {
        Certificate certificate = certificateRepository.findById(idCertificate).orElse(null);
        Skill skill = skillRepository.findById(idSkill).orElse(null);

        if (certificate != null && skill != null) {
            certificate.addSkill(skill);
            certificateRepository.save(certificate);
        }
    }

    @Override
    public void removeCertificateFromSkill(Long idCertificate, Long idSkill) {
        Certificate certificate = certificateRepository.findById(idCertificate).orElse(null);
        Skill skill = skillRepository.findById(idSkill).orElse(null);

        if (certificate != null && skill != null) {
            certificate.removeSkill(skill);
            certificateRepository.save(certificate);
        }
    }

    //... Verification
    @Override
    public boolean existsSkill(Long idSkill) {
        // Implement the logic to check if the skill with the given idSkill exists in the database
        // You can use a repository or any other mechanism to perform the existence check
        // Return true if the skill exists, false otherwise
        // For example:
        return skillRepository.existsById(idSkill);
    }

    @Override
    public boolean existsCertificate(Long idCertificate) {
        // Implement the logic to check if the certificate with the given idCertificate exists in the database
        // You can use a repository or any other mechanism to perform the existence check
        // Return true if the certificate exists, false otherwise
        // For example:
        return certificateRepository.existsById(idCertificate);
    }
}
