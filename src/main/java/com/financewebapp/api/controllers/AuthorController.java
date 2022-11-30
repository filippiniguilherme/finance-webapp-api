package com.financewebapp.api.controllers;

import com.financewebapp.api.dto.AuthorDTO;
import com.financewebapp.api.model.Author;
import com.financewebapp.api.service.AuthorService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/v1/author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;
    private static final Logger LOG = LoggerFactory.getLogger(AuthorController.class);

    @GetMapping("/list")
    @ApiOperation("Get all authors")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved authors."),
            @ApiResponse(code = 400, message = "Bad Request. Please check response body for further details."),
            @ApiResponse(code = 404, message = "Authors not found."),
            @ApiResponse(code = 500, message = "Internal Server Error. Please check response body for further details.")
    })
    public ResponseEntity<List<AuthorDTO>> getAuthors() throws EntityNotFoundException {
        LOG.info("Getting List of Author");
        return ResponseEntity.ok(authorService.getAuthors());
    }

    @PostMapping
    @ApiOperation("Save new author.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully save author."),
            @ApiResponse(code = 400, message = "Bad Request. Please check response body for further details."),
            @ApiResponse(code = 404, message = "Could not be saved."),
            @ApiResponse(code = 500, message = "Internal Server Error. Please check response body for further details.")
    })
    public ResponseEntity<String> post(@RequestBody Author author) throws EntityNotFoundException, IllegalArgumentException {
        LOG.info("Saving Author item.");
        AuthorDTO e = authorService.insert(author);

        return e != null ? new ResponseEntity<>("Successfully save author.", HttpStatus.OK) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @ApiOperation("Update author by ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated author."),
            @ApiResponse(code = 400, message = "Bad Request. Please check response body for further details."),
            @ApiResponse(code = 404, message = "Author not found."),
            @ApiResponse(code = 500, message = "Internal Server Error. Please check response body for further details.")
    })
    public ResponseEntity<String> put(@PathVariable("id") Long id, @RequestBody Author author) throws EntityNotFoundException, IllegalArgumentException {
        LOG.info("Updating Author item.");
        author.setAuthorId(id);
        AuthorDTO e = authorService.update(author, id);

        return e != null ? new ResponseEntity<>("Successfully update author.", HttpStatus.OK) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete author by ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted author."),
            @ApiResponse(code = 400, message = "Bad Request. Please check response body for further details."),
            @ApiResponse(code = 404, message = "Author not found."),
            @ApiResponse(code = 500, message = "Internal Server Error. Please check response body for further details.")
    })
    public ResponseEntity<AuthorDTO> delete(@PathVariable("id") Long id) throws EntityNotFoundException, IllegalArgumentException {
        LOG.info("Deleting Author item.");
        authorService.delete(id);
        return ResponseEntity.ok().build();
    }
}
