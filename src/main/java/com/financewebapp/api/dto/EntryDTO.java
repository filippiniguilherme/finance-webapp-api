package com.financewebapp.api.dto;

import com.financewebapp.api.model.Entry;
import lombok.Data;

import java.sql.Timestamp;

import org.modelmapper.ModelMapper;

@Data
public class EntryDTO {
    private Long entryId;

    private String entryName;
    private Timestamp entryDate;
    private Float entryValue;
    private Integer entryMonth;
    private Integer entryYear;
    private Long AuthorId;
    private Long CategoryId;


    public static EntryDTO create(Entry entry) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(entry, EntryDTO.class);
    }
}
