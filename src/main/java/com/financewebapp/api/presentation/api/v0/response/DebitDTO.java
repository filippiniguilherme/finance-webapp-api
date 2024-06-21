package com.financewebapp.api.presentation.api.v0.response;

import com.financewebapp.api.infrastructure.model.Debit;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.sql.Date;
import java.sql.Timestamp;

@Data
public class DebitDTO {
    private Long id;
    private String name;
    private Timestamp dateCreated;
    private Date date;
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
