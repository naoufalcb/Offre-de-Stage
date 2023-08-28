package com.example.repository;

import com.example.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface StudentRepository extends JpaRepository<Student,Long> {

    @Query("select s from Student s where s.account.id = :accountId")
    Student findByAccountId(@Param("accountId") Long accountId);
}
