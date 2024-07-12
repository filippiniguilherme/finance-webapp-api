package com.financewebapp.api.infrastructure;

import com.financewebapp.api.domain.EntryRepositoryService;
import com.financewebapp.api.infrastructure.model.Category;
import com.financewebapp.api.infrastructure.model.Entry;
import com.financewebapp.api.infrastructure.model.EntryDetail;
import com.financewebapp.api.infrastructure.repository.EntryRepository;
import com.financewebapp.api.presentation.api.v0.response.EntriesDTO;
import com.financewebapp.api.presentation.api.v0.response.EntryDTO;
import com.financewebapp.api.utils.PropertyBeanUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.internal.util.Assert;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EntryRepositoryAdapter implements EntryRepositoryService {

    private final EntryRepository entryRepository;

    private PropertyBeanUtils propertyBeanUtils;

    private EntryDetail calcDetail(EntriesDTO entries) {
        EntryDetail entryDetail = new EntryDetail();

        entryDetail.setCountEntries(entries.getEntries().size());
        entryDetail.setTotalEntries(entries.getEntries().stream().mapToDouble(Entry::getValue).sum());

        return entryDetail;
    }

    public EntriesDTO getEntries() {
        log.info("List Entries");
        EntriesDTO entriesDTO = new EntriesDTO();
        
        entriesDTO.setEntries(entryRepository.findAll());
        entriesDTO.setDetail(calcDetail(entriesDTO));

        return entriesDTO;
    }

    public EntriesDTO getEntriesByMonthAndYear(Integer month, Integer year) {
        log.info("List Entries By Month {} And Year {}", month, year);
        EntriesDTO entriesDTO = new EntriesDTO();
        
        entriesDTO.setEntries(entryRepository.findByMonthAndYear(month, year));
        entriesDTO.setDetail(calcDetail(entriesDTO));

        return entriesDTO;
    }

    public EntriesDTO getEntriesByMonthAndYearAndCategory(Integer month, Integer year, Category category) {
        log.info("List Entries By Month {}, Year {}, And CategoryId {}", month, year, category);
        EntriesDTO entriesDTO = new EntriesDTO();
        entriesDTO.setEntries(entryRepository.findByMonthAndYearAndCategory(month, year, category));
        entriesDTO.setDetail(calcDetail(entriesDTO));

        return entriesDTO;
    }

    public Entry insert(Entry entry) {
        log.info("Insert Entry: {}", entry);
        updateValueParentId(entry);
        return entryRepository.save(entry);
    }

    public void updateValueParentId(Entry entry) {
        log.info("Update Value Parent Id: {}", entry);

        Float entries = entryRepository.countValueByParentId(entry.getParentId());

        log.info("Entries: {}", entries);
    }

    public EntryDTO update(Entry entry, Long id) {
        log.info("ID: {}", id);
        Assert.notNull(id, "Unable to update the data");

        Optional<Entry> optional = entryRepository.findById(id);
        if(optional.isPresent()){
            Entry db = optional.get();

            db.setName(entry.getName());
            db.setValue(entry.getValue());
            db.setMonth(entry.getMonth());
            db.setYear(entry.getYear());
            db.setAuthor(entry.getAuthor());
            db.setCategory(entry.getCategory());

            log.info("Update Entry: {}", db.getId());

            entryRepository.save(db);
            return EntryDTO.create(db);
        } else {
            throw new RuntimeException("Unable to update the data");
        }
    }

    public void delete(Long id) {
        log.info("Delete Entry: {}", id);
        entryRepository.deleteById(id);
    }

    public EntryDTO patch(Entry entry) throws InvocationTargetException, IllegalAccessException {
        Assert.notNull(entry.getId(), "Unable to update the data");
        log.info("Patch Entry: {}", entry);

        Optional<Entry> optional = entryRepository.findById(entry.getId());
        if(optional.isPresent()) {
            Entry entryDB = optional.get();

            log.info("EntryDB: {}", entryDB, optional);

            propertyBeanUtils.compareEntry(entryDB, entry);

            log.info("Patch Entry: {}", entryDB);
            entryRepository.save(entryDB);
            return EntryDTO.create(entryDB);
        } else {
            throw new RuntimeException("Unable to update the data");
        }
    }
}