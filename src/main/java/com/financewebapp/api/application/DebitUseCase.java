package com.financewebapp.api.application;

import com.financewebapp.api.infrastructure.model.Category;
import com.financewebapp.api.infrastructure.model.Debit;
import com.financewebapp.api.presentation.api.v0.response.DebitDTO;
import com.financewebapp.api.presentation.api.v0.response.DebitsDTO;

import java.lang.reflect.InvocationTargetException;

public interface DebitUseCase {

    DebitsDTO getDebits();

    DebitsDTO getDebitsByMonthAndYear(Integer month, Integer year);

    DebitsDTO getDebitsByMonthAndYearAndCategory(Integer month, Integer year, Category category);

    Debit insert(Debit debit);

    DebitDTO update(Debit debit, Long id);

    void delete(Long id);

    DebitDTO patch(Long id, Debit debit) throws InvocationTargetException, IllegalAccessException;

}