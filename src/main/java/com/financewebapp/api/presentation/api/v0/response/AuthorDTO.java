package com.financewebapp.api.presentation.api.v0.response;

import com.financewebapp.api.infrastructure.model.Author;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class AuthorDTO {
    private Long authorId;
    private String authorName;

    public static AuthorDTO create(Author author) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(author, AuthorDTO.class);
    }
}
