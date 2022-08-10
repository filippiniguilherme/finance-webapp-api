package com.financewebapp.api.dto;

import com.financewebapp.api.model.Debit;
import lombok.Data;

import java.sql.Timestamp;

import org.modelmapper.ModelMapper;

@Data
public class DebitDTO {
    private Long DebitId;

    private String DebitName;
    private Timestamp DebitDate;
    private Float DebitValue;
    private Integer DebitMonth;
    private Integer DebitYear;
    private Long AuthorId;
    private Long CategoryId;
    
    public static DebitDTO create(Debit debit) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(debit, DebitDTO.class);
    }
}
