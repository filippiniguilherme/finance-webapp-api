package com.financewebapp.api.infrastructure;

import com.financewebapp.api.domain.DebitRepositoryService;
import com.financewebapp.api.infrastructure.model.Category;
import com.financewebapp.api.infrastructure.model.Debit;
import com.financewebapp.api.infrastructure.model.DebitDetail;
import com.financewebapp.api.infrastructure.repository.DebitRepository;
import com.financewebapp.api.presentation.api.v0.response.DebitDTO;
import com.financewebapp.api.presentation.api.v0.response.DebitsDTO;
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
public class DebitRepositoryAdapter implements DebitRepositoryService {

    private final DebitRepository debitRepository;

    private PropertyBeanUtils propertyBeanUtils;

    private DebitDetail calcDetail(DebitsDTO debits) {
        DebitDetail debitDetail = new DebitDetail();

        debitDetail.setCountDebits(debits.getDebits().size());
        debitDetail.setTotalDebits(debits.getDebits().stream().mapToDouble(Debit::getValue).sum());

        return debitDetail;
    }

    public DebitsDTO getDebits() {
        log.info("List Debits");
        DebitsDTO debitsDTO = new DebitsDTO();
        
        debitsDTO.setDebits(debitRepository.findAll());
        debitsDTO.setDetail(calcDetail(debitsDTO));

        return debitsDTO;
    }

    public DebitsDTO getDebitsByMonthAndYear(Integer month, Integer year) {
        log.info("List Debits By Month {} And Year {}", month, year);
        DebitsDTO debitsDTO = new DebitsDTO();
        
        debitsDTO.setDebits(debitRepository.findByMonthAndYear(month, year));
        debitsDTO.setDetail(calcDetail(debitsDTO));

        return debitsDTO;
    }

    public DebitsDTO getDebitsByMonthAndYearAndCategory(Integer month, Integer year, Category category) {
        log.info("List Debits By Month {}, Year {} And CategoryId {}", month, year, category);
        DebitsDTO debitsDTO = new DebitsDTO();
        
        debitsDTO.setDebits(debitRepository.findByMonthAndYearAndCategory(month, year, category));
        debitsDTO.setDetail(calcDetail(debitsDTO));

        return debitsDTO;
    }

    public Debit insert(Debit debit) {
        log.info("Insert Debit: {}", debit);
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
            db.setCategory(debit.getCategory());

            log.info("Update Debit: {}", db.toString());

            debitRepository.save(db);
            return DebitDTO.create(db);
        } else {
            throw new RuntimeException("Unable to update the data");
        }
    }

    public void delete(Long id) {
        log.info("Delete Debit: {}", id);
        debitRepository.deleteById(id);
    }

    public DebitDTO patch(Long id, Debit debit) throws InvocationTargetException, IllegalAccessException {
        Assert.notNull(id, "Unable to update the data");

        Optional<Debit> optional = debitRepository.findById(id);
        if(optional.isPresent()) {
            Debit debitDB = optional.get();

            propertyBeanUtils.compareDebit(debitDB, debit);

            log.info("Patch Debit: {}", debitDB);
            debitRepository.save(debitDB);
            return DebitDTO.create(debitDB);
        } else {
            throw new RuntimeException("Unable to update the data");
        }
    }
}