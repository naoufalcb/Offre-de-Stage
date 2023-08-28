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
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdCertificate;
    private String certificateTitle;
    private String issuingOrganisation;
    private String credentialId;
    private String credentialUrl;
    private Date issueDate;
    private Date expirationDate;


    // ... Student
    @ManyToMany(mappedBy = "certificates",fetch = FetchType.LAZY)
    @JsonIgnoreProperties
    private List<Student> Students = new ArrayList<>();

    // ... Skill
    @ManyToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @JsonIgnoreProperties
    @JoinTable(name = "certificate_skills",
            joinColumns = {
                    @JoinColumn(name = "idCertificate")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "idSkill")
            }
    )

    private List<Skill> skills = new ArrayList<>();

    // Add a skill to a certificate
    public void addSkill(Skill skill) {
        skills.add(skill);
        skill.getCertificates().add(this);
    }

    // Remove a skill from a certificate
    public void removeSkill(Skill skill) {
        skills.remove(skill);
        skill.getCertificates().remove(this);
    }
}
