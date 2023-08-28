package com.example.repository;

import com.example.entities.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill,Long> {
    public Skill findBySkillName (String skillName);
}
