package com.financewebapp.api.repository;

import com.financewebapp.api.model.Category;
import com.financewebapp.api.model.Debit;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DebitRepository extends JpaRepository<Debit, Long> {
    List<Debit> findByMonthAndYear(Integer month, Integer year);

    List<Debit> findByMonthAndYearAndCategory(Integer month, Integer year, Category category);
}
