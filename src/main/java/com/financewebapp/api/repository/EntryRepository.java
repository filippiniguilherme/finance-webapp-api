package com.financewebapp.api.repository;

import com.financewebapp.api.model.Entry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryRepository extends JpaRepository<Entry, Long> {
    List<Entry> findByEntryMonthAndEntryYear(Integer month, Integer year);
}
