package com.financewebapp.api.dto;

import com.financewebapp.api.model.Balance;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class BalanceDTO {
    private Double BalanceValue;
    private Double TotalDebits;
    private Number CountDebits;
    private Double TotalEntries;
    private Number CountEntries;
    private Number Month;
    private Number Year;

    public static BalanceDTO create(Balance balance) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(balance, BalanceDTO.class);
    }
}
