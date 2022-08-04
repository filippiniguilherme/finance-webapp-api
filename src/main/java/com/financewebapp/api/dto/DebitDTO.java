package com.financewebapp.api.dto;

import com.financewebapp.api.model.Debit;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class DebitDTO {
    private Long id;

    private String name;
    private Double value;

    public static DebitDTO create(Debit debits) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(debits, DebitDTO.class);
    }
}
