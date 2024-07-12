package com.financewebapp.api.presentation.api.v0.response;

import com.financewebapp.api.infrastructure.model.Entry;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.sql.Date;
import java.sql.Timestamp;

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
