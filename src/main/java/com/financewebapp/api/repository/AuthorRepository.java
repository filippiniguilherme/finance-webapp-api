package com.financewebapp.api.repository;

import com.financewebapp.api.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
