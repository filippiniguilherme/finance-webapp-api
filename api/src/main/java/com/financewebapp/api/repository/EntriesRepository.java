package com.financewebapp.api.repository;

import com.financewebapp.api.model.Entries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntriesRepository extends JpaRepository<Entries, Long> {
}
