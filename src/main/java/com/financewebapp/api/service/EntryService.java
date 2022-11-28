package com.financewebapp.api.service;

import com.financewebapp.api.dto.EntriesDTO;
import com.financewebapp.api.dto.EntryDTO;
import com.financewebapp.api.model.Entry;
import com.financewebapp.api.model.EntryDetail;
import com.financewebapp.api.repository.EntryRepository;
import com.financewebapp.api.utils.PropertyBeanUtils;
import org.modelmapper.internal.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

@Service
public class EntryService {
    @Autowired
    private EntryRepository entryRepository;
    private static final Logger LOG = LoggerFactory.getLogger(EntryService.class);

    private PropertyBeanUtils propertyBeanUtils;

    private EntryDetail calcDetail(EntriesDTO entries) {
        EntryDetail entryDetail = new EntryDetail();

        entryDetail.setCountEntries(entries.getEntries().size());
        entryDetail.setTotalEntries(entries.getEntries().stream().mapToDouble(Entry::getValue).sum());

        return entryDetail;
    }

    public EntriesDTO getEntries() {
        LOG.info("List Entries");
        EntriesDTO entriesDTO = new EntriesDTO();
        
        entriesDTO.setEntries(entryRepository.findAll());
        entriesDTO.setDetail(calcDetail(entriesDTO));

        return entriesDTO;
    };

    public EntriesDTO getEntriesByMonthAndYear(Integer month, Integer year) {
        LOG.info("List Entries By Month {} And Year {}", month, year);
        EntriesDTO entriesDTO = new EntriesDTO();
        
        entriesDTO.setEntries(entryRepository.findByMonthAndYear(month, year));
        entriesDTO.setDetail(calcDetail(entriesDTO));

        return entriesDTO;
    };

    public EntriesDTO getEntriesByMonthAndYearAndCategory(Integer month, Integer year, Long categoryId) {
        LOG.info("List Entries By Month {}, Year {}, And CategoryId {}", month, year, categoryId);
        EntriesDTO entriesDTO = new EntriesDTO();
        
        entriesDTO.setEntries(entryRepository.findByMonthAndYearAndCategoryId(month, year, categoryId));
        entriesDTO.setDetail(calcDetail(entriesDTO));

        return entriesDTO;
    };

    public Entry insert(Entry entry) {
        LOG.info("Insert Entry: {}", entry);
        return entryRepository.save(entry);
    }

    public EntryDTO update(Entry entry, Long id) {
        Assert.notNull(id, "Unable to update the data");

        Optional<Entry> optional = entryRepository.findById(id);
        if(optional.isPresent()){
            Entry db = optional.get();

            db.setName(entry.getName());
            db.setValue(entry.getValue());
            db.setMonth(entry.getMonth());
            db.setYear(entry.getYear());
            db.setAuthor(entry.getAuthor());
            db.setCategoryId(entry.getCategoryId());

            LOG.info("Update Entry: {}", db.getId());

            entryRepository.save(db);
            return EntryDTO.create(db);
        } else {
            throw new RuntimeException("Unable to update the data");
        }
    }

    public void delete(Long id) {
        LOG.info("Delete Entry: {}", id);
        entryRepository.deleteById(id);
    }

    public EntryDTO patch(Long id, Entry entry) throws InvocationTargetException, IllegalAccessException {
        Assert.notNull(id, "Unable to update the data");

        Optional<Entry> optional = entryRepository.findById(id);
        if(optional.isPresent()) {
            Entry entryDB = optional.get();

            propertyBeanUtils.compareEntry(entryDB, entry);

            LOG.info("Patch Entry: {}", entryDB);
            entryRepository.save(entryDB);
            return EntryDTO.create(entryDB);
        } else {
            throw new RuntimeException("Unable to update the data");
        }
    }
}
