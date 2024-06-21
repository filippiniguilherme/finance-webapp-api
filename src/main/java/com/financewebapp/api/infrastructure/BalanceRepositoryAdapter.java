package com.financewebapp.api.infrastructure;

import com.financewebapp.api.domain.BalanceRepositoryService;
import com.financewebapp.api.presentation.api.v0.response.BalanceDTO;
import com.financewebapp.api.presentation.api.v0.response.DebitsDTO;
import com.financewebapp.api.presentation.api.v0.response.EntriesDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
@Service
@RequiredArgsConstructor
public class BalanceRepositoryAdapter implements BalanceRepositoryService {

    private final EntryRepositoryAdapter entryRepositoryAdapter;

    private final DebitRepositoryAdapter debitRepositoryAdapter;

    public BalanceDTO getBalance() {
        log.info("List Balance");
        BalanceDTO balanceDTO = new BalanceDTO();

        DebitsDTO debits = debitRepositoryAdapter.getDebits();
        EntriesDTO entries = entryRepositoryAdapter.getEntries();
        log.info("Balance value: {}", (entries.getDetail().getTotalEntries()) - debits.getDetail().getTotalDebits());
        Double balanceValue = BigDecimal.valueOf((entries.getDetail().getTotalEntries() - debits.getDetail().getTotalDebits())).setScale(2, RoundingMode.HALF_UP).doubleValue();

        balanceDTO.setDebitDetail(debits.getDetail());
        balanceDTO.setEntryDetail(entries.getDetail());
        balanceDTO.setBalanceValue(balanceValue);

        balanceDTO.setMonth(null);
        balanceDTO.setYear(null);
        
        return balanceDTO;
    }

    public BalanceDTO getBalanceByMonthAndYear(Integer month, Integer year) {
        log.info("List Balance");
        BalanceDTO balanceDTO = new BalanceDTO();

        DebitsDTO debits = debitRepositoryAdapter.getDebitsByMonthAndYear(month, year);
        EntriesDTO entries = entryRepositoryAdapter.getEntriesByMonthAndYear(month, year);

        log.info("Balance value: {}", (entries.getDetail().getTotalEntries() - debits.getDetail().getTotalDebits()));
        Double balanceValue = BigDecimal.valueOf((entries.getDetail().getTotalEntries() - debits.getDetail().getTotalDebits())).setScale(2, RoundingMode.HALF_UP).doubleValue();
        balanceDTO.setDebitDetail(debits.getDetail());
        balanceDTO.setEntryDetail(entries.getDetail());
        balanceDTO.setBalanceValue(balanceValue);

        balanceDTO.setMonth(month);
        balanceDTO.setYear(year);
        
        return balanceDTO;
    }
}