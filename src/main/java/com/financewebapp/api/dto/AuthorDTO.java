package com.financewebapp.api.dto;

import com.financewebapp.api.model.Author;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class AuthorDTO {
    private Long AuthorId;
    private String AuthorName;

    public static AuthorDTO create(Author author) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(author, AuthorDTO.class);
    }
}
