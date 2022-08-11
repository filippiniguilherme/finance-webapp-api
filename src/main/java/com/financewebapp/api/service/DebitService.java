package com.financewebapp.api.service;

import com.financewebapp.api.dto.DebitDTO;
import com.financewebapp.api.model.Debit;
import com.financewebapp.api.repository.DebitRepository;

import org.modelmapper.internal.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DebitService {

    @Autowired
    private DebitRepository debitRepository;
    private static final Logger LOG = LoggerFactory.getLogger(DebitRepository.class);


    public List<DebitDTO> getDebits() {
        LOG.info("List Debits");
        return debitRepository.findAll().stream().map(DebitDTO::create).collect(Collectors.toList());
    };

    public List<Debit> getDebitsByMonthAndYear(Integer month, Integer year) {
        LOG.info("List Debits By Month {} And Year {}", month, year);
        return debitRepository.findByDebitMonthAndDebitYear(month, year);
    };

    public DebitDTO insert(Debit debit) {
        LOG.info("Insert Debit: {}", debit);
        return DebitDTO.create(debitRepository.save(debit));
    }

    public DebitDTO update(Debit debit, Long id) {
        Assert.notNull(id, "Unable to update the data");

        Optional<Debit> optional = debitRepository.findById(id);
        if(optional.isPresent()){
            Debit db = optional.get();

            db.setDebitName(debit.getDebitName());
            db.setDebitValue(debit.getDebitValue());
            db.setDebitMonth(debit.getDebitMonth());
            db.setDebitYear(debit.getDebitYear());
            db.setAuthorId(debit.getAuthorId());
            db.setCategoryId(debit.getCategoryId());

            LOG.info("Update Debit: {}", db.toString());

            debitRepository.save(db);
            return DebitDTO.create(db);
        } else {
            throw new RuntimeException("Unable to update the data");
        }
    }

    public void delete(Long id) {
        LOG.info("Delete Debit: {}", id);
        debitRepository.deleteById(id);
    }

    public DebitDTO patch(Long id, Debit debit) {
        Assert.notNull(id, "Unable to update the data");

        Optional<Debit> optional = debitRepository.findById(id);
        if(optional.isPresent()) {
            Debit db = optional.get();
            
            if(debit.getDebitName() != null) {
                db.setDebitName(debit.getDebitName());
            }

            if(debit.getDebitValue() != null) {
                db.setDebitValue(debit.getDebitValue());
            }

            if(debit.getDebitMonth() != null) {
                db.setDebitMonth(debit.getDebitMonth());
            }

            if(debit.getDebitYear() != null) {
                db.setDebitYear(debit.getDebitYear());
            }

            if(debit.getAuthorId() != null) {
                db.setAuthorId(debit.getAuthorId());
            }

            if(debit.getCategoryId() != null) {
                db.setCategoryId(debit.getCategoryId());
            }

            LOG.info("Patch Debit: {}", db.toString());
            debitRepository.save(db);
            return DebitDTO.create(db);
        } else {
            throw new RuntimeException("Unable to update the data");
        }
    }
}
