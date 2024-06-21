package com.financewebapp.api.application;

import com.financewebapp.api.infrastructure.model.Author;
import com.financewebapp.api.presentation.api.v0.response.AuthorDTO;

import java.util.List;

public interface AuthorUseCase {

    List<AuthorDTO> getAuthors();

    AuthorDTO insert(Author author);

    AuthorDTO update(Author author, Long id);

    void delete(Long id);

}