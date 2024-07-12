package com.financewebapp.api.infrastructure.repository;

import com.financewebapp.api.infrastructure.model.Category;
import com.financewebapp.api.infrastructure.model.Debit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DebitRepository extends JpaRepository<Debit, Long> {
    List<Debit> findByMonthAndYear(Integer month, Integer year);

    List<Debit> findByMonthAndYearAndCategory(Integer month, Integer year, Category category);
}
