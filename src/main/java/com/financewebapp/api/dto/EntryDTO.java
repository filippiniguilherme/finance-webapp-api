package com.financewebapp.api.dto;

import com.financewebapp.api.model.Entry;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

import org.modelmapper.ModelMapper;

@Data
public class EntryDTO {
    private Long id;

    private String name;
    private Timestamp dateCreated;
    private Date date;
    private Float value;
    private Integer month;
    private Integer year;
    private Long authorId;
    private Long categoryId;


    public static EntryDTO create(Entry entry) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(entry, EntryDTO.class);
    }
}
