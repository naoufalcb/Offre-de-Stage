package com.example.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentSkillDTO
{
    private Long idStudent;
    private Long idSkill;
}
