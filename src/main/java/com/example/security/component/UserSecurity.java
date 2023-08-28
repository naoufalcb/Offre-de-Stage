package com.example.security.component;

import com.example.entities.Student;
import com.example.repository.AccountRepository;
import com.example.repository.StudentRepository;
import com.example.security.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class UserSecurity {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    AccountRepository accountRepository;

    public boolean checkUserId(Authentication authentication,Long idStudent) {
        String username =   authentication.getName();
        Account account = accountRepository.findByUsername(username);
        Student cond = studentRepository.findById(account.getId()).get();
        return cond.getIdStudent().equals(idStudent);
    }

    public Long getUserId(Authentication authentication) {
        String username =   authentication.getName();
        Account account = accountRepository.findByUsername(username);
        Student cond = studentRepository.findByAccountId(account.getId());
        return cond.getIdStudent();
    }

}
