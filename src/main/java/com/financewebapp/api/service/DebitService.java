package com.financewebapp.api.service;

import com.financewebapp.api.dto.DebitDTO;
import com.financewebapp.api.dto.DebitsDTO;
import com.financewebapp.api.model.Debit;
import com.financewebapp.api.model.DebitDetail;
import com.financewebapp.api.repository.DebitRepository;
import com.financewebapp.api.utils.PropertyBeanUtils;
import org.modelmapper.internal.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

@Service
public class DebitService {

    @Autowired
    private DebitRepository debitRepository;

    private PropertyBeanUtils propertyBeanUtils;
    private static final Logger LOG = LoggerFactory.getLogger(DebitService.class);

    private DebitDetail calcDetail(DebitsDTO debits) {
        DebitDetail debitDetail = new DebitDetail();

        debitDetail.setCountDebits(debits.getDebits().size());
        debitDetail.setTotalDebits(debits.getDebits().stream().mapToDouble(Debit::getValue).sum());   

        return debitDetail;
    }

    public DebitsDTO getDebits() {
        LOG.info("List Debits");
        DebitsDTO debitsDTO = new DebitsDTO();
        
        debitsDTO.setDebits(debitRepository.findAll());
        debitsDTO.setDetail(calcDetail(debitsDTO));

        return debitsDTO;
    }

    public DebitsDTO getDebitsByMonthAndYear(Integer month, Integer year) {
        LOG.info("List Debits By Month {} And Year {}", month, year);
        DebitsDTO debitsDTO = new DebitsDTO();
        
        debitsDTO.setDebits(debitRepository.findByMonthAndYear(month, year));
        debitsDTO.setDetail(calcDetail(debitsDTO));

        return debitsDTO;
    }

    public DebitsDTO getDebitsByMonthAndYearAndCategory(Integer month, Integer year, Long categoryId) {
        LOG.info("List Debits By Month {}, Year {} And CategoryId {}", month, year, categoryId);
        DebitsDTO debitsDTO = new DebitsDTO();
        
        debitsDTO.setDebits(debitRepository.findByMonthAndYearAndCategoryId(month, year, categoryId));
        debitsDTO.setDetail(calcDetail(debitsDTO));

        return debitsDTO;
    }

    public Debit insert(Debit debit) {
        LOG.info("Insert Debit: {}", debit);
        return debitRepository.save(debit);
    }

    public DebitDTO update(Debit debit, Long id) {
        Optional<Debit> optional = debitRepository.findById(id);
        if(optional.isPresent()){
            Debit db = optional.get();

            db.setName(debit.getName());
            db.setValue(debit.getValue());
            db.setMonth(debit.getMonth());
            db.setYear(debit.getYear());
            db.setAuthor(debit.getAuthor());
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

    public DebitDTO patch(Long id, Debit debit) throws InvocationTargetException, IllegalAccessException {
        Assert.notNull(id, "Unable to update the data");

        Optional<Debit> optional = debitRepository.findById(id);
        if(optional.isPresent()) {
            Debit debitDB = optional.get();

            propertyBeanUtils.compareDebit(debitDB, debit);

            LOG.info("Patch Debit: {}", debitDB);
            debitRepository.save(debitDB);
            return DebitDTO.create(debitDB);
        } else {
            throw new RuntimeException("Unable to update the data");
        }
    }
}
