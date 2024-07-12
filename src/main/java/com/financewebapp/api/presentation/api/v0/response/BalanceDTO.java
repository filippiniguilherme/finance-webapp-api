package com.financewebapp.api.presentation.api.v0.response;

import com.financewebapp.api.infrastructure.model.Balance;
import com.financewebapp.api.infrastructure.model.DebitDetail;
import com.financewebapp.api.infrastructure.model.EntryDetail;
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