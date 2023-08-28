package com.example.controller;

import com.example.entities.Student;
import com.example.model.*;
import com.example.repository.AccountRepository;
import com.example.repository.RoleRepository;
import com.example.security.Account;
import com.example.security.JWTGenerator;
import com.example.security.Role;
import com.example.service.AccountService;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AccountService accountService;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTGenerator jwtGenerator;
    @Autowired
    StudentService studentService;

    @PostMapping("/register")//done
    public ResponseEntity<String> register(@RequestBody AppUserDto appUserDto) {
        if (accountRepository.existsByUsername(appUserDto.getUsername())){
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }

        Account account = new Account();
        account.setUsername(appUserDto.getUsername());
        account.setPassword(passwordEncoder.encode((appUserDto.getPassword())));

        Role roles = roleRepository.findByRoleName("STUDENT");
        account.setRoles(Collections.singletonList(roles));

        accountRepository.save(account);

        return new ResponseEntity<>("Student Registered Success!", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AppUserDto appUserDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        appUserDto.getUsername(),
                        appUserDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDTO(token),HttpStatus.OK);
    }

    @PostMapping("/registerStudent")//done
    public ResponseEntity<String> registerStudent(@RequestBody StudentAccountDTO studentAccountDTO) {
        if (accountRepository.existsByUsername(studentAccountDTO.getUsername())){
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }
        Student student = new Student();
        student.setFirstName(studentAccountDTO.getFirstName());
        student.setLastName(studentAccountDTO.getLastName());
        student.setInternee(studentAccountDTO.getInternee());


        Account account = new Account();
        account.setUsername(studentAccountDTO.getUsername());
        account.setPassword(passwordEncoder.encode((studentAccountDTO.getPassword())));

        Role roles = roleRepository.findByRoleName("STUDENT");
        account.setRoles(Collections.singletonList(roles));

        accountRepository.save(account);
        student.setAccount(account);
        studentService.createStudent(student);
        return new ResponseEntity<>("Student Registered Success!", HttpStatus.OK);
    }
}
