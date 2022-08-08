package com.financewebapp.api.service;

import com.financewebapp.api.dto.EntryDTO;
import com.financewebapp.api.model.Entry;
import com.financewebapp.api.repository.EntryRepository;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EntryService {
    @Autowired
    private EntryRepository entryRepository;

    public List<EntryDTO> getEntries() {
        return entryRepository.findAll().stream().map(EntryDTO::create).collect(Collectors.toList());
    };

    public EntryDTO insert(Entry entry) {
        return EntryDTO.create(entryRepository.save(entry));
    }

    public EntryDTO update(Entry entry, Long id) {
        Assert.notNull(id, "Unable to update the data");

        Optional<Entry> optional = entryRepository.findById(id);
        if(optional.isPresent()){
            Entry db = optional.get();

            db.setEntryName(entry.getEntryName());
            db.setEntryValue(entry.getEntryValue());

            System.out.println("Entry id: " + db.getEntryId());

            entryRepository.save(db);
            return EntryDTO.create(db);
        } else {
            throw new RuntimeException("Unable to update the data");
        }
    }

    public void delete(Long id) {
        entryRepository.deleteById(id);
    }

    public EntryDTO patch(Long id, Entry entry) {
        Assert.notNull(id, "Unable to update the data");

        Optional<Entry> optional = entryRepository.findById(id);
        if(optional.isPresent()) {
            Entry db = optional.get();

            if(entry.getEntryValue() != null) {
                db.setEntryValue(entry.getEntryValue());
            }

            if(entry.getEntryName() != null) {
                db.setEntryName(entry.getEntryName());
            }

            entryRepository.save(db);
            return EntryDTO.create(db);
        } else {
            throw new RuntimeException("Unable to update the data");
        }
    }
}
