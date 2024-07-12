package com.financewebapp.api.domain;

import com.financewebapp.api.presentation.api.v0.response.BalanceDTO;

public interface BalanceRepositoryService {

    BalanceDTO getBalance();

    BalanceDTO getBalanceByMonthAndYear(Integer month, Integer year);

}