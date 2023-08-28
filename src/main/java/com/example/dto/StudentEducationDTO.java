package com.example.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentEducationDTO
{
    private Long idStudent;
    private Long idEducation;
}
