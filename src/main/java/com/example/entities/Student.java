package com.example.entities;

import com.example.security.Account;
import com.fasterxml.jackson.annotation.*;
import javax.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idStudent")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("idStudent") // The property name for serialization
    private Long idStudent;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    private Boolean internee;


    //...........Try JsonIgnoreInfo............

    // ... Skill
    @ManyToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @JoinTable(name = " Student_skill",
    joinColumns = {
            @JoinColumn(name = "idStudent")
    },
    inverseJoinColumns = {
            @JoinColumn(name = "idSkill")
    }
    )
    @JsonIgnoreProperties
    private List<Skill> skills = new ArrayList<>();

    // ... Language
    @ManyToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @JoinTable(name = " Student_language",
            joinColumns = {
                    @JoinColumn(name = "idStudent")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "idLanguage")
            }
    )
    @JsonIgnoreProperties
    private List<Language> languages = new ArrayList<>();


    // ... Contact
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    @JsonIgnoreProperties
    private List<Contact> contacts = new ArrayList<>();

    // ... Education
    @ManyToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @JoinTable(name = " Student_education",
            joinColumns = {
                    @JoinColumn(name = "idStudent")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "idEducation")
            }
    )
    @JsonIgnoreProperties
    private List<Education> educations = new ArrayList<>();

    // ... Certificate
    @ManyToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @JoinTable(name = " Student_certificate",
            joinColumns = {
                    @JoinColumn(name = "idStudent")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "idCertificate")
            }
    )
    @JsonIgnoreProperties
    private List<Certificate> certificates = new ArrayList<>();

    // ... Experience
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    @JsonIgnoreProperties
    private List<Experience> experiences = new ArrayList<>();

    //... Location
    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties
    private Location location;

    //... Account
    @OneToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
    private Account account;

    @Override
    public boolean equals(Object o) {
        Student that = (Student) o;
        return Objects.equals(idStudent, that.idStudent);
    }

}
