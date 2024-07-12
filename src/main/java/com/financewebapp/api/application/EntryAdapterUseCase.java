package com.financewebapp.api.application;

import com.financewebapp.api.domain.EntryRepositoryService;
import com.financewebapp.api.infrastructure.model.Category;
import com.financewebapp.api.infrastructure.model.Entry;
import com.financewebapp.api.presentation.api.v0.response.EntriesDTO;
import com.financewebapp.api.presentation.api.v0.response.EntryDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;

@Slf4j
@Service
@RequiredArgsConstructor
public class EntryAdapterUseCase implements EntryUseCase {

    private final EntryRepositoryService entryRepositoryService;

    public EntriesDTO getEntries() {
        return entryRepositoryService.getEntries();
    }

    public EntriesDTO getEntriesByMonthAndYear(Integer month, Integer year) {
        return entryRepositoryService.getEntriesByMonthAndYear(month, year);
    }

    public EntriesDTO getEntriesByMonthAndYearAndCategory(Integer month, Integer year, Category category) {
        return entryRepositoryService.getEntriesByMonthAndYearAndCategory(month, year, category);
    }

    public Entry insert(Entry entry) {
        return entryRepositoryService.insert(entry);
    }

    public void updateValueParentId(Entry entry) {
        entryRepositoryService.updateValueParentId(entry);
    }

    public EntryDTO update(Entry entry, Long id) {
        return entryRepositoryService.update(entry, id);
    }

    public void delete(Long id) {
        entryRepositoryService.delete(id);
    }

    public EntryDTO patch(Entry entry) throws InvocationTargetException, IllegalAccessException {
        return entryRepositoryService.patch(entry);
    }
}