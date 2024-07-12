package com.financewebapp.api.application;

import com.financewebapp.api.domain.AuthorRepositoryService;
import com.financewebapp.api.infrastructure.model.Author;
import com.financewebapp.api.presentation.api.v0.response.AuthorDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorAdapterUseCase implements AuthorUseCase {

    private final AuthorRepositoryService authorRepositoryService;

    public List<AuthorDTO> getAuthors() {
        return authorRepositoryService.getAuthors();
    }

    public AuthorDTO insert(Author author) {
        return authorRepositoryService.insert(author);
    }

    public AuthorDTO update(Author author, Long id) {
        return authorRepositoryService.update(author, id);
    }

    public void delete(Long id) {
        authorRepositoryService.delete(id);
    }
}