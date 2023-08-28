package com.example.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javax.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idExperience")
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idExperience;

    private String experienceTitle;
    private String employmentType;
    private String companyName;
    private String companyLocation;
    private String locationType;
    private Date startDateExperience;
    private Date endDateExperience;

    @ManyToOne
    @JoinColumn(name = "idStudent")
    @JsonIgnore
    private Student student;


}






