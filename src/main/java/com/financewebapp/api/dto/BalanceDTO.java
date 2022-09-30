package com.financewebapp.api.dto;

import com.financewebapp.api.model.Balance;
import com.financewebapp.api.model.DebitDetail;
import com.financewebapp.api.model.EntryDetail;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class BalanceDTO {
    private DebitDetail debitDetail;
    private EntryDetail entryDetail;
    private Double balanceValue;
    private Number month;
    private Number year;    

    public static BalanceDTO create(Balance balance) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(balance, BalanceDTO.class);
    }
}
