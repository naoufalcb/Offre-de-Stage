package com.example.service;

import com.example.security.Account;
import com.example.security.Role;

import java.util.List;

public interface AccountService {

    public Account addNewAccount(Account appUser);
    public Role addNewRole(Role appRole);
    public void addRoleToAccount(String username,String roleName);
    public Account loadUserByUsername(String username);
    public List<Account> listAccounts();
}
