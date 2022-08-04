package com.financewebapp.api.repository;

import com.financewebapp.api.model.Debit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DebitRepository extends JpaRepository<Debit, Long> {
}
