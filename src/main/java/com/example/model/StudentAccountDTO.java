package com.example.model;

import lombok.Data;

@Data
public class StudentAccountDTO {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Boolean internee;
}
