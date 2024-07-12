package com.financewebapp.api.application;

import com.financewebapp.api.domain.BalanceRepositoryService;
import com.financewebapp.api.presentation.api.v0.response.BalanceDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BalanceAdapterUseCase implements BalanceUseCase {

    private final BalanceRepositoryService balanceRepositoryService;

    public BalanceDTO getBalance() {
        return balanceRepositoryService.getBalance();
    }

    public BalanceDTO getBalanceByMonthAndYear(Integer month, Integer year) {
        return balanceRepositoryService.getBalanceByMonthAndYear(month, year);
    }
}