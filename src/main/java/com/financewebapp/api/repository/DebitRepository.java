package com.financewebapp.api.repository;

import com.financewebapp.api.model.Debit;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DebitRepository extends JpaRepository<Debit, Long> {
    List<Debit> findByDebitMonthAndDebitYear(Integer DebitMonth, Integer DebitYear);
}
