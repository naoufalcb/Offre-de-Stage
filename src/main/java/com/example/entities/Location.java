package com.example.entities;

import javax.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLocation;

    private String address1;
    private String address2;
    private String city;
    private String region;
    private String zipCode;

    // One-to-one bidirectional relationship with Student
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idStudent")
    private Student student;

    // Constructors, getters, and setters
}

