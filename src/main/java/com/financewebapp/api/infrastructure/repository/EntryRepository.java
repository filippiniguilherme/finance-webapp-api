package com.financewebapp.api.infrastructure.repository;

import com.financewebapp.api.infrastructure.model.Category;
import com.financewebapp.api.infrastructure.model.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EntryRepository extends JpaRepository<Entry, Long> {
    @Query(
        value = "SELECT SUM(value) FROM Entries e WHERE e.parentId = ?1",
        nativeQuery = true
    )
    Float countValueByParentId(Entry parentId);

    List<Entry> findByMonthAndYear(Integer month, Integer year);

    List<Entry> findByMonthAndYearAndCategory(Integer month, Integer year, Category category);

}