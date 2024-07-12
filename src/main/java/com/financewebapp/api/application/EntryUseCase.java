package com.financewebapp.api.application;

import com.financewebapp.api.infrastructure.model.Category;
import com.financewebapp.api.infrastructure.model.Entry;
import com.financewebapp.api.presentation.api.v0.response.EntriesDTO;
import com.financewebapp.api.presentation.api.v0.response.EntryDTO;

import java.lang.reflect.InvocationTargetException;

public interface EntryUseCase {

    EntriesDTO getEntries();

    EntriesDTO getEntriesByMonthAndYear(Integer month, Integer year);

    EntriesDTO getEntriesByMonthAndYearAndCategory(Integer month, Integer year, Category category);

    Entry insert(Entry entry);

    void updateValueParentId(Entry entry);

    EntryDTO update(Entry entry, Long id);

    void delete(Long id);

    EntryDTO patch(Entry entry) throws InvocationTargetException, IllegalAccessException;

}