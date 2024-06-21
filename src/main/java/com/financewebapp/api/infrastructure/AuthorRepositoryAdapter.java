package com.financewebapp.api.infrastructure;

import com.financewebapp.api.domain.AuthorRepositoryService;
import com.financewebapp.api.infrastructure.model.Author;
import com.financewebapp.api.infrastructure.repository.AuthorRepository;
import com.financewebapp.api.presentation.api.v0.response.AuthorDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.internal.util.Assert;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorRepositoryAdapter implements AuthorRepositoryService {

    private final AuthorRepository authorRepository;

    public List<AuthorDTO> getAuthors() {
        log.info("List Authors");
        return authorRepository.findAll().stream().map(AuthorDTO::create).collect(Collectors.toList());
    }

    public AuthorDTO insert(Author author) {
        log.info("Insert Author: {}", author.getAuthorName());
        return AuthorDTO.create(authorRepository.save(author));
    }

    public AuthorDTO update(Author author, Long id) {
        Assert.notNull(id, "Unable to update the data");

        Optional<Author> optional = authorRepository.findById(id);
        if(optional.isPresent()){
            Author db = optional.get();

            db.setAuthorName(author.getAuthorName());

            log.info("Update Author: {}", db.getAuthorId());

            authorRepository.save(db);
            return AuthorDTO.create(db);
        } else {
            throw new RuntimeException("Unable to update the data");
        }
    }

    public void delete(Long id) {
        log.info("Delete Author: {}", id);
        authorRepository.deleteById(id);
    }
}