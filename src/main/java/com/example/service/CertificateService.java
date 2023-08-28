package com.example.service;

import com.example.entities.Certificate;
import com.example.entities.Education;
import com.example.entities.Skill;
import com.example.entities.Student;
import com.example.exeption.CertificateNotFound;
import com.example.exeption.StudentNotFound;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CertificateService {
    //----------------------POST----------------------
    public Certificate createCertificate(Certificate newCertificate);
    public void addCertificateToStudent(Long idStudent,Long idCertificate);
    //----------------------PUT----------------------
    public ResponseEntity<Certificate> updateCertificate(Long idCertificate, Certificate certificateDetails);
    //----------------------GET----------------------
    public List<Certificate> getAllCertificates();
    public Certificate getCertificateById(Long idCertificate) throws CertificateNotFound;
    public List<Student> getCertificateStudents(Long idCertificate) throws CertificateNotFound;
    public List<Skill> getCertificateSkills(Long idCertificate) throws CertificateNotFound;
    //----------------------DELETE----------------------
    public void deleteCertificate(Long idCertificate);
    public void deleteCertificateToStudent(Long idStudent,Long idCertificate);
    
    //... Verification
    boolean existsStudent(Long idStudent);

    boolean existsCertificate(Long idCertificate);
}
