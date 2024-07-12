package com.financewebapp.api.infrastructure.repository;

import com.financewebapp.api.infrastructure.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
