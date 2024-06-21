package com.financewebapp.api.application;

import com.financewebapp.api.presentation.api.v0.response.BalanceDTO;

public interface BalanceUseCase {

    BalanceDTO getBalance();

    BalanceDTO getBalanceByMonthAndYear(Integer month, Integer year);

}