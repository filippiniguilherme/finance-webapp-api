package com.financewebapp.api.dto;

import com.financewebapp.api.model.Entry;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class EntryDTO {
    private Long id;

    private String name;
    private Double value;

    public static EntryDTO create(Entry entry) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(entry, EntryDTO.class);
    }
}
