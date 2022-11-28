package com.financewebapp.api.dto;

import com.financewebapp.api.model.Debit;
import lombok.Data;

import java.sql.Timestamp;

import org.modelmapper.ModelMapper;

@Data
public class DebitDTO {
    private Long id;
    private String name;
    private Timestamp date;
    private Float value;
    private Integer month;
    private Integer year;
    private Long author;
    private Long categoryId;
    
    public static DebitDTO create(Debit debit) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(debit, DebitDTO.class);
    }
}
