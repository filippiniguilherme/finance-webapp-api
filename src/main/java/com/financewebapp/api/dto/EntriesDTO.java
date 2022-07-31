package com.financewebapp.api.dto;

import com.financewebapp.api.model.Entries;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class EntriesDTO {
    private Long id;

    private String name;
    private Double value;

    public static EntriesDTO create(Entries entries) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(entries, EntriesDTO.class);
    }
}
