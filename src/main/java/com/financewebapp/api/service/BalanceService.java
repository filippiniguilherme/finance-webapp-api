package com.financewebapp.api.service;

import com.financewebapp.api.dto.BalanceDTO;
import com.financewebapp.api.model.Debit;
import com.financewebapp.api.model.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BalanceService {
    @Autowired
    private EntryService entryService;
    @Autowired
    private DebitService debitService;

    private static final Logger LOG = LoggerFactory.getLogger(BalanceService.class);

    public BalanceDTO getBalance(Integer month, Integer year) {
        LOG.info("List Balance");
        BalanceDTO balanceDTO = new BalanceDTO();

        List<Debit> debit = debitService.getDebitsByMonthAndYear(month, year);
        List<Entry> entry = entryService.getEntriesByMonthAndYear(month, year);
        
        balanceDTO.setCountDebits(debit.size());
        balanceDTO.setTotalDebits(debit.stream().mapToDouble(x -> x.getDebitValue()).sum());   

        balanceDTO.setCountEntries(entry.size());
        balanceDTO.setTotalEntries(entry.stream().mapToDouble(x -> x.getEntryValue()).limit(2).sum());

        balanceDTO.setMonth(month);
        balanceDTO.setYear(year);
        balanceDTO.setBalanceValue(balanceDTO.getTotalEntries() - balanceDTO.getTotalDebits());
        
        return balanceDTO;
    };
}
