package com.example.service;

public interface SkillCertificateService {
    void addSkillToCertificate(Long idSkill, Long idCertificate);
    void removeSkillFromCertificate(Long idSkill, Long idCertificate);
    void addCertificateToSkill(Long idCertificate, Long idSkill);
    void removeCertificateFromSkill(Long idCertificate, Long idSkill);

    //... Verification
    boolean existsSkill(Long idSkill);

    boolean existsCertificate(Long idCertificate);
}
