package com.example.repository;

import com.example.entities.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



import java.util.List;

public interface ExperienceRepository extends JpaRepository<Experience,Long> {
    // Custom query methods
    List<Experience> findByCompanyName(String companyName);
    List<Experience> findByEmploymentType(String employmentType);
    List<Experience> findByLocationType(String locationType);

    // Custom query to get experiences by student id
    @Query("SELECT e FROM Experience e WHERE e.student.idStudent = :idStudent")
    List<Experience> findByIdStudent(Long idStudent);
}
