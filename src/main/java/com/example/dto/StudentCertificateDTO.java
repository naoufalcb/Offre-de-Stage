package com.example.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentCertificateDTO
{
    private Long idStudent;
    private Long idCertificate;
}
