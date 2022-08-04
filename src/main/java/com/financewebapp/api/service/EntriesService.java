package com.financewebapp.api.service;

import com.financewebapp.api.dto.EntriesDTO;
import com.financewebapp.api.model.Entries;
import com.financewebapp.api.repository.EntriesRepository;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EntriesService {
    @Autowired
    private EntriesRepository entriesRepository;

    public List<EntriesDTO> getEntries() {
        return entriesRepository.findAll().stream().map(EntriesDTO::create).collect(Collectors.toList());
    };

    public EntriesDTO insert(Entries entries) {
        return EntriesDTO.create(entriesRepository.save(entries));
    }

    public EntriesDTO update(Entries entries, Long id) {
        Assert.notNull(id, "Unable to update the data");

        Optional<Entries> optional = entriesRepository.findById(id);
        if(optional.isPresent()){
            Entries db = optional.get();

            db.setName(entries.getName());
            db.setValue(entries.getValue());

            System.out.println("Entry id: " + db.getId());

            entriesRepository.save(db);
            return EntriesDTO.create(db);
        } else {
            throw new RuntimeException("Unable to update the data");
        }
    }

    public void delete(Long id) {
        entriesRepository.deleteById(id);
    }

    public EntriesDTO patch(Long id, Entries entries) {
        Assert.notNull(id, "Unable to update the data");

        Optional<Entries> optional = entriesRepository.findById(id);
        if(optional.isPresent()) {
            Entries db = optional.get();

            if(entries.getValue() != null) {
                db.setValue(entries.getValue());
            }

            if(entries.getName() != null) {
                db.setName(entries.getName());
            }

            entriesRepository.save(db);
            return EntriesDTO.create(db);
        } else {
            throw new RuntimeException("Unable to update the data");
        }
    }
}
