package com.financewebapp.api.controllers;

import com.financewebapp.api.dto.AuthorDTO;
import com.financewebapp.api.model.Author;
import com.financewebapp.api.service.AuthorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;
    private static final Logger LOG = LoggerFactory.getLogger(AuthorController.class);

    @GetMapping("/list")
    public ResponseEntity<List<AuthorDTO>> getAuthors() {
        LOG.info("Getting List of Author");
        return ResponseEntity.ok(authorService.getAuthors());
    }

    @PostMapping
    public ResponseEntity<AuthorDTO> post(@RequestBody Author author) {
        LOG.info("Posting item of Author");
        AuthorDTO e = authorService.insert(author);

        return e != null ? ResponseEntity.created(null).build() : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDTO> put(@PathVariable("id") Long id, @RequestBody Author author) {
        LOG.info("Puting item of Author");
        author.setAuthorId(id);
        AuthorDTO e = authorService.update(author, id);

        return e != null ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AuthorDTO> delete(@PathVariable("id") Long id) {
        LOG.info("Deleting item of Author");
        authorService.delete(id);
        return ResponseEntity.ok().build();
    }
}
