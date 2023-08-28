package com.example.repository;

import com.example.security.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {
    public Account findByUsername(String username);
    Boolean existsByUsername(String username);
}
