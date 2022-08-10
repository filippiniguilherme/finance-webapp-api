package com.financewebapp.api.service;

import com.financewebapp.api.dto.EntryDTO;
import com.financewebapp.api.model.Entry;
import com.financewebapp.api.repository.EntryRepository;
import org.modelmapper.internal.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EntryService {
    @Autowired
    private EntryRepository entryRepository;
    private static final Logger LOG = LoggerFactory.getLogger(EntryRepository.class);

    public List<EntryDTO> getEntries() {
        LOG.info("List Entries");
        return entryRepository.findAll().stream().map(EntryDTO::create).collect(Collectors.toList());
    };

    public List<Entry> getEntriesByMonthAndYear(Integer month, Integer year) {
        LOG.info("List Entries By Month {} And Year {}", month, year);

        return entryRepository.findByEntryMonthAndEntryYear(month, year);
    };

    public EntryDTO insert(Entry entry) {
        LOG.info("Insert Entry: {}", entry);
        return EntryDTO.create(entryRepository.save(entry));
    }

    public EntryDTO update(Entry entry, Long id) {
        Assert.notNull(id, "Unable to update the data");

        Optional<Entry> optional = entryRepository.findById(id);
        if(optional.isPresent()){
            Entry db = optional.get();

            db.setEntryName(entry.getEntryName());
            db.setEntryValue(entry.getEntryValue());
            db.setEntryMonth(entry.getEntryMonth());
            db.setEntryYear(entry.getEntryYear());
            db.setAuthorId(entry.getAuthorId());
            db.setCategoryId(entry.getCategoryId());

            LOG.info("Update Entry: " + db.getEntryId());

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

    public EntryDTO patch(Long id, Entry entry) {
        Assert.notNull(id, "Unable to update the data");

        Optional<Entry> optional = entryRepository.findById(id);
        if(optional.isPresent()) {
            Entry db = optional.get();
            
            if(entry.getEntryName() != null) {
                db.setEntryName(entry.getEntryName());
            }

            if(entry.getEntryValue() != null) {
                db.setEntryValue(entry.getEntryValue());
            }

            if(entry.getEntryMonth() != null) {
                db.setEntryMonth(entry.getEntryMonth());
            }

            if(entry.getEntryYear() != null) {
                db.setEntryYear(entry.getEntryYear());
            }

            if(entry.getAuthorId() != null) {
                db.setAuthorId(entry.getAuthorId());
            }

            if(entry.getCategoryId() != null) {
                db.setCategoryId(entry.getCategoryId());
            }

            LOG.info("Patch Entry: {}", db.toString());
            entryRepository.save(db);
            return EntryDTO.create(db);
        } else {
            throw new RuntimeException("Unable to update the data");
        }
    }
}
