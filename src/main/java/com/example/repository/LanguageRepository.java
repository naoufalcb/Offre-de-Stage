package com.example.repository;

import com.example.entities.Language;
import org.springframework.data.jpa.repository.JpaRepository;
public interface LanguageRepository extends JpaRepository<Language,Long> {
}
