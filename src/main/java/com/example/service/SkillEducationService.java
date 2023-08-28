package com.example.service;

public interface SkillEducationService {
    void addSkillToEducation(Long idSkill, Long idEducation);
    void removeSkillFromEducation(Long idSkill, Long idEducation);
    void addEducationToSkill(Long idEducation, Long idSkill);
    void removeEducationFromSkill(Long idEducation, Long idSkill);

    //... Verification
    boolean existsSkill(Long idSkill);

    boolean existsEducation(Long idEducation);
}
