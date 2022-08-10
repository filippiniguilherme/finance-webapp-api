package com.financewebapp.api.dto;

import com.financewebapp.api.model.Entry;
import lombok.Data;

import java.sql.Timestamp;

import org.modelmapper.ModelMapper;

@Data
public class EntryDTO {
    private Long EntryId;

    private String EntryName;
    private Timestamp EntryDate;
    private Float EntryValue;
    private Integer EntryMonth;
    private Integer EntryYear;
    private Long AuthorId;
    private Long CategoryId;


    public static EntryDTO create(Entry entry) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(entry, EntryDTO.class);
    }
}
