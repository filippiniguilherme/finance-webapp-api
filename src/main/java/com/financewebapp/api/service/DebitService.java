package com.financewebapp.api.service;

import com.financewebapp.api.dto.DebitDTO;
import com.financewebapp.api.model.Debit;
import com.financewebapp.api.repository.DebitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DebitService {

    @Autowired
    private DebitRepository debitsRepository;

    public List<DebitDTO> getDebits() {
        return debitsRepository.findAll().stream().map(DebitDTO::create).collect(Collectors.toList());
    }

    public DebitDTO insert(Debit debits) {
        return DebitDTO.create(debitsRepository.save(debits));
    }
}
