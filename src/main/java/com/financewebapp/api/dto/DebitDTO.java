package com.financewebapp.api.dto;

import com.financewebapp.api.model.Debit;
import lombok.Data;

import java.sql.Timestamp;

import org.modelmapper.ModelMapper;

@Data
public class DebitDTO {
    private Long debitId;

    private String debitName;
    private Timestamp debitDate;
    private Float debitValue;
    private Integer debitMonth;
    private Integer debitYear;
    private Long AuthorId;
    private Long CategoryId;
    
    public static DebitDTO create(Debit debit) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(debit, DebitDTO.class);
    }
}
