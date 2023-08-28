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
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLanguage;
    @Column(nullable = false)
    private String languageName;
    @Column(nullable = false)
    private String languageLevel;
    @Column(nullable = false)
    private String languageSource;



    @ManyToMany(mappedBy = "languages",fetch = FetchType.LAZY)
    @JsonIgnoreProperties
    private List<Student> Students = new ArrayList<>();

}
