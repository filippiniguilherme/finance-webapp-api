package com.financewebapp.api.service;

import com.financewebapp.api.dto.BalanceDTO;
import com.financewebapp.api.dto.DebitsDTO;
import com.financewebapp.api.dto.EntriesDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
@Service
public class BalanceService {
    @Autowired
    private EntryService entryService;
    @Autowired
    private DebitService debitService;

    private static final Logger LOG = LoggerFactory.getLogger(BalanceService.class);

    public BalanceDTO getBalance() {
        LOG.info("List Balance");
        BalanceDTO balanceDTO = new BalanceDTO();

        DebitsDTO debits = debitService.getDebits();
        EntriesDTO entries = entryService.getEntries();
        LOG.info("Balance value: {}", (entries.getDetail().getTotalEntries()) - debits.getDetail().getTotalDebits());
        Double balanceValue = BigDecimal.valueOf((entries.getDetail().getTotalEntries() - debits.getDetail().getTotalDebits())).setScale(2, RoundingMode.HALF_UP).doubleValue();

        balanceDTO.setDebitDetail(debits.getDetail());
        balanceDTO.setEntryDetail(entries.getDetail());
        balanceDTO.setBalanceValue(balanceValue);

        balanceDTO.setMonth(null);
        balanceDTO.setYear(null);
        
        return balanceDTO;
    }

    public BalanceDTO getBalanceByMonthAndYear(Integer month, Integer year) {
        LOG.info("List Balance");
        BalanceDTO balanceDTO = new BalanceDTO();

        DebitsDTO debits = debitService.getDebitsByMonthAndYear(month, year);
        EntriesDTO entries = entryService.getEntriesByMonthAndYear(month, year);
        
        LOG.info("Balance value: {}", (entries.getDetail().getTotalEntries() - debits.getDetail().getTotalDebits()));
        Double balanceValue = BigDecimal.valueOf((entries.getDetail().getTotalEntries() - debits.getDetail().getTotalDebits())).setScale(2, RoundingMode.HALF_UP).doubleValue();
        balanceDTO.setDebitDetail(debits.getDetail());
        balanceDTO.setEntryDetail(entries.getDetail());
        balanceDTO.setBalanceValue(balanceValue);

        balanceDTO.setMonth(month);
        balanceDTO.setYear(year);
        
        return balanceDTO;
    }
}
