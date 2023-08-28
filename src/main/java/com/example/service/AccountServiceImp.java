package com.example.service;

import com.example.repository.AccountRepository;
import com.example.repository.RoleRepository;
import com.example.security.Account;
import com.example.security.MyPasswordEncoder;
import com.example.security.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AccountServiceImp implements AccountService{

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    MyPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Account addNewAccount(Account appUser) {
        String pw = appUser.getPassword();
        appUser.setPassword(bCryptPasswordEncoder.encode(pw));
        return accountRepository.save(appUser);
    }

    @Override
    public Role addNewRole(Role appRole) {
        return roleRepository.save(appRole);
    }

    @Override
    public void addRoleToAccount(String username, String roleName) {
        Account user = accountRepository.findByUsername(username);
        Role appRole = roleRepository.findByRoleName(roleName);
        user.getRoles().add(appRole);
    }

    @Override
    public Account loadUserByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    @Override
    public List<Account> listAccounts() {
        return (List<Account>) accountRepository.findAll();
    }
}
