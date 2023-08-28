package com.example.service;

import com.example.entities.Education;
import com.example.entities.Skill;
import com.example.repository.EducationRepository;
import com.example.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillEducationServiceImp implements SkillEducationService{

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private EducationRepository educationRepository;

    @Override
    public void addSkillToEducation(Long idSkill, Long idEducation) {
        Skill skill = skillRepository.findById(idSkill).orElse(null);
        Education education = educationRepository.findById(idEducation).orElse(null);

        if (skill != null && education != null) {
            skill.addEducation(education);
            skillRepository.save(skill);
        }
    }

    @Override
    public void removeSkillFromEducation(Long idSkill, Long idEducation) {
        Skill skill = skillRepository.findById(idSkill).orElse(null);
        Education education = educationRepository.findById(idEducation).orElse(null);

        if (skill != null && education != null) {
            skill.removeEducation(education);
            skillRepository.save(skill);
        }
    }

    @Override
    public void addEducationToSkill(Long idEducation, Long idSkill) {
        Education education = educationRepository.findById(idEducation).orElse(null);
        Skill skill = skillRepository.findById(idSkill).orElse(null);

        if (education != null && skill != null) {
            education.addSkill(skill);
            educationRepository.save(education);
        }
    }

    @Override
    public void removeEducationFromSkill(Long idEducation, Long idSkill) {
        Education education = educationRepository.findById(idEducation).orElse(null);
        Skill skill = skillRepository.findById(idSkill).orElse(null);

        if (education != null && skill != null) {
            education.removeSkill(skill);
            educationRepository.save(education);
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
    public boolean existsEducation(Long idEducation) {
        // Implement the logic to check if the education with the given idEducation exists in the database
        // You can use a repository or any other mechanism to perform the existence check
        // Return true if the education exists, false otherwise
        // For example:
        return educationRepository.existsById(idEducation);
    }
}
