package com.financewebapp.api.service;

import com.financewebapp.api.dto.BalanceDTO;
import com.financewebapp.api.dto.DebitDTO;
import com.financewebapp.api.dto.DebitsDTO;
import com.financewebapp.api.dto.EntriesDTO;
import com.financewebapp.api.model.Debit;
import com.financewebapp.api.model.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
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
        LOG.info("Balance value: {}", (debits.getDetail().getTotalDebits() - entries.getDetail().getTotalEntries()));
        Double balanceValue = new BigDecimal((debits.getDetail().getTotalDebits() - entries.getDetail().getTotalEntries())).setScale(2, RoundingMode.HALF_UP).doubleValue();

        balanceDTO.setDebitDetail(debits.getDetail());
        balanceDTO.setEntryDetail(entries.getDetail());
        balanceDTO.setBalanceValue(balanceValue);

        balanceDTO.setMonth(null);
        balanceDTO.setYear(null);
        
        return balanceDTO;
    };

    public BalanceDTO getBalanceByMonthAndYear(Integer month, Integer year) {
        LOG.info("List Balance");
        BalanceDTO balanceDTO = new BalanceDTO();

        DebitsDTO debits = debitService.getDebitsByMonthAndYear(month, year);
        EntriesDTO entries = entryService.getEntriesByMonthAndYear(month, year);
        
        LOG.info("Balance value: {}", (debits.getDetail().getTotalDebits() - entries.getDetail().getTotalEntries()));
        // Double balanceValue = new BigDecimal((debits.getDetail().getTotalDebits() - entries.getDetail().getTotalEntries())).setScale(2, RoundingMode.HALF_UP).doubleValue();
        Double balanceValue = (debits.getDetail().getTotalDebits() - entries.getDetail().getTotalEntries());
        balanceDTO.setDebitDetail(debits.getDetail());
        balanceDTO.setEntryDetail(entries.getDetail());
        balanceDTO.setBalanceValue(balanceValue);

        balanceDTO.setMonth(month);
        balanceDTO.setYear(year);
        
        return balanceDTO;
    };
}
