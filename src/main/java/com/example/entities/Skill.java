package com.example.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idSkill")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSkill;
    @Column(nullable = false)
    private String skillName;


    // ... Student
    @ManyToMany(mappedBy = "skills",fetch = FetchType.LAZY)
    @JsonIgnoreProperties
    private List<Student> Students = new ArrayList<>();
    // ... Certificate
    @ManyToMany(mappedBy = "skills",fetch = FetchType.LAZY)
    @JsonIgnoreProperties
    private List<Certificate> certificates = new ArrayList<>();
    // ... Education
    @ManyToMany(mappedBy = "skills")
    @JsonIgnoreProperties
    private List<Education> educations = new ArrayList<>();

    // Add a certificate to a skill
    public void addCertificate(Certificate certificate) {
        certificates.add(certificate);
        certificate.getSkills().add(this);
    }
    // Remove a certificate from a skill
    public void removeCertificate(Certificate certificate) {
        certificates.remove(certificate);
        certificate.getSkills().remove(this);
    }

    // Add an education to a skill
    public void addEducation(Education education) {
        educations.add(education);
        education.getSkills().add(this);
    }
    // Remove an education from a skill
    public void removeEducation(Education education) {
        educations.remove(education);
        education.getSkills().remove(this);
    }
}
