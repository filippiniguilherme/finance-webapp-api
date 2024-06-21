package com.financewebapp.api.application;

import com.financewebapp.api.domain.DebitRepositoryService;
import com.financewebapp.api.infrastructure.model.Category;
import com.financewebapp.api.infrastructure.model.Debit;
import com.financewebapp.api.presentation.api.v0.response.DebitDTO;
import com.financewebapp.api.presentation.api.v0.response.DebitsDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;

@Slf4j
@Service
@RequiredArgsConstructor
public class DebitAdapterUseCase implements DebitUseCase {

    private final DebitRepositoryService debitRepositoryService;

    public DebitsDTO getDebits() {
        return debitRepositoryService.getDebits();
    }

    public DebitsDTO getDebitsByMonthAndYear(Integer month, Integer year) {
        return debitRepositoryService.getDebitsByMonthAndYear(month, year);
    }

    public DebitsDTO getDebitsByMonthAndYearAndCategory(Integer month, Integer year, Category category) {
        return debitRepositoryService.getDebitsByMonthAndYearAndCategory(month, year, category);
    }

    public Debit insert(Debit debit) {
        return debitRepositoryService.insert(debit);
    }

    public DebitDTO update(Debit debit, Long id) {
        return debitRepositoryService.update(debit, id);
    }

    public void delete(Long id) {
        debitRepositoryService.delete(id);
    }

    public DebitDTO patch(Long id, Debit debit) throws InvocationTargetException, IllegalAccessException {
        return debitRepositoryService.patch(id, debit);
    }
}