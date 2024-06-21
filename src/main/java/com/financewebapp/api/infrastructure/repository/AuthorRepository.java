package com.financewebapp.api.infrastructure.repository;

import com.financewebapp.api.infrastructure.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
