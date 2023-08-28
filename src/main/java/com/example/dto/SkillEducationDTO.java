package com.example.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SkillEducationDTO
{
    private Long idSkill;
    private Long idEducation;
}
