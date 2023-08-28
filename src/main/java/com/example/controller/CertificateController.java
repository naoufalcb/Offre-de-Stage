package com.example.controller;

import com.example.dto.StudentCertificateDTO;
import com.example.entities.*;
import com.example.entities.Certificate;
import com.example.exeption.CertificateNotFound;
import com.example.exeption.basic.ValidationException;
import com.example.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/certificates")
public class CertificateController {

    @Autowired
    CertificateService certificateService;


    //----------------------POST----------------------
    @PostMapping("/create_certificate")//done
    public Certificate createCertificate(@RequestBody Certificate newCertificate) {

        return certificateService.createCertificate(newCertificate);
    }

    @PostMapping("/addCertificateToStudent")
    public ResponseEntity<String> addCertificateToStudent(@RequestBody StudentCertificateDTO studentCertificateDTO) {
        Long idStudent = studentCertificateDTO.getIdStudent();
        Long idCertificate = studentCertificateDTO.getIdCertificate();

        if (idStudent == null || idCertificate == null) {
            throw new ValidationException("idStudent and idCertificate cannot be Null.");
        }

        if (!certificateService.existsCertificate(idCertificate) && !certificateService.existsStudent(idStudent)) {
            throw new ValidationException("Student with id " + idStudent + " and Certificate with id "+idCertificate+" Not Found.");
        }

        if (!certificateService.existsStudent(idStudent)) {
            throw new ValidationException("Student with id " + idStudent + " Not Found.");
        }

        if (!certificateService.existsCertificate(idCertificate)) {
            throw new ValidationException("Certificate with id " + idCertificate + " Not Found.");
        }

        certificateService.addCertificateToStudent(idStudent, idCertificate);
        String responseMessage = "Certificate : " + idCertificate + " is added to Student : " + idStudent;
        return ResponseEntity.ok(responseMessage);
    }
    //----------------------PUT----------------------
    @PutMapping("/edit_certificate")//done
    public String updateCertificate(@RequestParam Long idCertificate,
                                  @RequestBody Certificate certificateDetails) throws CertificateNotFound {
        if (!certificateService.existsCertificate(idCertificate)) {
            throw new CertificateNotFound("Certificate with id " + idCertificate + " Not Found.");
        }
        certificateService.updateCertificate(idCertificate,certificateDetails);
        return "Update of Certificate : "+idCertificate+" Completed";
    }

    //----------------------GET----------------------
    @GetMapping("/find_all_certificate")//done
    public ResponseEntity<List<Certificate>> certificateList(){

        List<Certificate> certificates = certificateService.getAllCertificates();
        if (!certificates.isEmpty()) {
            return ResponseEntity.ok(certificates);
        } else {
            throw new CertificateNotFound("No Certificate Found. ");
        }
    }

    @GetMapping("/find_certificate")//done
    public Certificate CertificateById(@RequestParam("idCertificate") Long idCertificate){

        return certificateService.getCertificateById(idCertificate);

    }

    @GetMapping("/{idCertificate}/students")//done
    public ResponseEntity<List<Student>> getCertificateStudents(@PathVariable Long idCertificate) {
        List<Student> students = certificateService.getCertificateStudents(idCertificate);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{idCertificate}/skills")//done
    public ResponseEntity<List<Skill>> getCertificateSkills(@PathVariable Long idCertificate) {
        List<Skill> skills = certificateService.getCertificateSkills(idCertificate);
        return ResponseEntity.ok(skills);
    }

    //----------------------DELETE----------------------
    @DeleteMapping("/{idCertificate}")//done
    public ResponseEntity<String> deleteCertificate(@PathVariable Long idCertificate) throws CertificateNotFound {
        Certificate certificate = certificateService.getCertificateById(idCertificate);
        if (certificate != null) {
            certificateService.deleteCertificate(idCertificate);
            return ResponseEntity.ok("Certificate "+idCertificate+" deleted successfully");
        } else {
            throw new CertificateNotFound("Certificate with Id : " + idCertificate + " Not Found. ");
        }
    }

    @DeleteMapping("/deleteCertificateToStudent")//done
    public ResponseEntity<String> deleteCertificateToStudent(@RequestBody StudentCertificateDTO studentCertificateDTO){
        Long idStudent = studentCertificateDTO.getIdStudent();
        Long idCertificate = studentCertificateDTO.getIdCertificate();

        if (idStudent == null || idCertificate == null) {
            throw new ValidationException("idStudent and idCertificate cannot be Null.");
        }

        if (!certificateService.existsCertificate(idCertificate) && !certificateService.existsStudent(idStudent)) {
            throw new ValidationException("Student with id " + idStudent + " and Certificate with id "+idCertificate+" Not Found.");
        }

        if (!certificateService.existsStudent(idStudent)) {
            throw new ValidationException("Student with id " + idStudent + " Not Found.");
        }

        if (!certificateService.existsCertificate(idCertificate)) {
            throw new ValidationException("Certificate with id " + idCertificate + " Not Found.");
        }

        certificateService.deleteCertificateToStudent(idStudent, idCertificate);
        String responseMessage = "Certificate : " + idCertificate + " is deleted to Student : " + idStudent;
        return ResponseEntity.ok(responseMessage);
    }
}
