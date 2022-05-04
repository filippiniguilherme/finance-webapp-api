package com.financewebapp.api.service;

import com.financewebapp.api.dto.EntriesDTO;
import com.financewebapp.api.model.Entries;
import com.financewebapp.api.repository.EntriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
}
