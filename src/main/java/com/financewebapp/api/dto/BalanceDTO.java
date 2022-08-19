package com.financewebapp.api.dto;

import com.financewebapp.api.model.Balance;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class BalanceDTO {
    private Double balanceValue;
    private Double totalDebits;
    private Number countDebits;
    private Double totalEntries;
    private Number countEntries;
    private Number month;
    private Number year;

    public static BalanceDTO create(Balance balance) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(balance, BalanceDTO.class);
    }
}
