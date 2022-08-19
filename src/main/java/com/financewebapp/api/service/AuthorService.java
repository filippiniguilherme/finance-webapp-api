package com.financewebapp.api.service;

import com.financewebapp.api.dto.AuthorDTO;
import com.financewebapp.api.model.Author;
import com.financewebapp.api.repository.AuthorRepository;
import org.modelmapper.internal.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;
    private static final Logger LOG = LoggerFactory.getLogger(AuthorService.class);

    public List<AuthorDTO> getAuthors() {
        LOG.info("List Authors");
        return authorRepository.findAll().stream().map(AuthorDTO::create).collect(Collectors.toList());
    };

    public AuthorDTO insert(Author author) {
        LOG.info("Insert Author: {}", author.getAuthorName());
        return AuthorDTO.create(authorRepository.save(author));
    }

    public AuthorDTO update(Author author, Long id) {
        Assert.notNull(id, "Unable to update the data");

        Optional<Author> optional = authorRepository.findById(id);
        if(optional.isPresent()){
            Author db = optional.get();

            db.setAuthorName(author.getAuthorName());

            LOG.info("Update Author: {}", db.getAuthorId());

            authorRepository.save(db);
            return AuthorDTO.create(db);
        } else {
            throw new RuntimeException("Unable to update the data");
        }
    }

    public void delete(Long id) {
        LOG.info("Delete Author: {}", id);
        authorRepository.deleteById(id);
    }
}
