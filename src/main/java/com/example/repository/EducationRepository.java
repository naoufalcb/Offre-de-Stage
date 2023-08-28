package com.example.repository;

import com.example.entities.Education;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface EducationRepository extends JpaRepository<Education,Long> {
    public Education findBySchool (String school);
    public Education findByRegulatedAccess(Boolean regulatedAccess);
    public Education findByFieldOfStudy (String fieldOfStudy);
    public Education findByFinishDateEducation (Date finishDateEducation);
    boolean existsBySchool(String school);

}
