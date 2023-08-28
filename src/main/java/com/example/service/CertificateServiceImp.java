package com.example.service;

import com.example.entities.Certificate;
import com.example.entities.Certificate;
import com.example.entities.Skill;
import com.example.entities.Student;
import com.example.exeption.CertificateNotFound;
import com.example.exeption.StudentNotFound;
import com.example.repository.CertificateRepository;
import com.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CertificateServiceImp implements CertificateService{
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    CertificateRepository certificateRepository;

    //----------------------POST----------------------
    @Override
    public Certificate createCertificate(Certificate newCertificate) {
        return certificateRepository.save(newCertificate);
    }

    @Override
    public void addCertificateToStudent(Long IdStudent, Long IdCertificate) {
        Student student = studentRepository.findById(IdStudent).get();
        Certificate certificate = certificateRepository.findById(IdCertificate).get();
        student.getCertificates().add(certificate);
        studentRepository.save(student);
    }

    //----------------------PUT----------------------
    @Override
    public ResponseEntity<Certificate> updateCertificate(Long IdCertificate, Certificate certificateDetails) {
        Certificate updateCertificate = certificateRepository.findById(IdCertificate).get();

        updateCertificate.setCertificateTitle(certificateDetails.getCertificateTitle());
        updateCertificate.setIssuingOrganisation(certificateDetails.getIssuingOrganisation());
        updateCertificate.setCredentialId(certificateDetails.getCredentialId());
        updateCertificate.setCredentialUrl(certificateDetails.getCredentialUrl());
        updateCertificate.setIssueDate(certificateDetails.getIssueDate());
        updateCertificate.setExpirationDate(certificateDetails.getExpirationDate());

        certificateRepository.save(updateCertificate);
        return ResponseEntity.ok(updateCertificate);
    }

    //----------------------GET----------------------
    @Override
    public List<Certificate> getAllCertificates() {

        return certificateRepository.findAll();
    }


    @Override
    public Certificate getCertificateById(Long idCertificate) {
        return certificateRepository.findById(idCertificate)
                .orElseThrow(()-> new CertificateNotFound("Certificate with Id : "+idCertificate+" Not Found. "));
    }

    public List<Student> getCertificateStudents(Long idCertificate) {
        Certificate certificate = certificateRepository.findById(idCertificate).orElse(null);
        if (certificate != null) {
            return certificate.getStudents();
        }else {
            throw new CertificateNotFound("Certificate with Id : " + idCertificate + " Not Found. ");
        }
    }

    public List<Skill> getCertificateSkills(Long idCertificate) {
        Certificate certificate = certificateRepository.findById(idCertificate).orElse(null);
        if (certificate != null) {
            return new ArrayList<>(certificate.getSkills());
        }else {
            throw new CertificateNotFound("Certificate with Id : " + idCertificate + " Not Found. ");
        }
    }

    //----------------------DELETE----------------------
    @Override
    public void deleteCertificate(Long IdCertificate) {
        certificateRepository.deleteById(IdCertificate);
    }

    @Override
    public void deleteCertificateToStudent(Long IdStudent, Long IdCertificate) {

        Student student = studentRepository.findById(IdStudent).get();
        Certificate certificate = certificateRepository.findById(IdCertificate).get();
        student.getCertificates().remove(certificate);
        studentRepository.save(student);
    }

    //... Verification
    @Override
    public boolean existsStudent(Long idStudent) {
        // Implement the logic to check if the student with the given idStudent exists in the database
        // You can use a repository or any other mechanism to perform the existence check
        // Return true if the student exists, false otherwise
        // For example:
        return studentRepository.existsById(idStudent);
    }

    @Override
    public boolean existsCertificate(Long idCertificate) {
        // Implement the logic to check if the language with the given idEducation exists in the database
        // You can use a repository or any other mechanism to perform the existence check
        // Return true if the language exists, false otherwise
        // For example:
        return certificateRepository.existsById(idCertificate);
    }

}
