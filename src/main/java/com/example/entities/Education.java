package com.example.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdEducation;
    private String school;
    private Boolean regulatedAccess;
    private String fieldOfStudy;
    private String grade;
    private String activities;
    private String educationDescription;
    private Date startDateEducation;
    private Date finishDateEducation;


    // ... Student
    @ManyToMany(mappedBy = "educations",fetch = FetchType.LAZY)
    @JsonIgnoreProperties
    private List<Student> Students = new ArrayList<>();
    // ... Skill
    @ManyToMany
    @JoinTable(
            name = "education_skills",
            joinColumns = @JoinColumn(name = "idEducation"),
            inverseJoinColumns = @JoinColumn(name = "idSkill")
    )
    @JsonIgnoreProperties
    private List<Skill> skills = new ArrayList<>();
    // Add a skill to a certificate
    public void addSkill(Skill skill) {
        skills.add(skill);
        skill.getEducations().add(this);
    }

    // Remove a skill from a certificate
    public void removeSkill(Skill skill) {
        skills.remove(skill);
        skill.getEducations().remove(this);
    }
}
