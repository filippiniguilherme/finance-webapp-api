package com.financewebapp.api.presentation.api;

import com.financewebapp.api.application.AuthorAdapterUseCase;
import com.financewebapp.api.infrastructure.model.Author;
import com.financewebapp.api.presentation.api.v0.response.AuthorDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@Slf4j
@CrossOrigin(maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/author")
public class AuthorController {

    private final AuthorAdapterUseCase authorAdapterUseCase;

    @GetMapping("/list")
    @Operation(description = "Get all authors")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved authors."),
            @ApiResponse(responseCode = "400", description = "Bad Request. Please check response body for further details."),
            @ApiResponse(responseCode = "404", description = "Authors not found."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error. Please check response body for further details.")
    })
    public ResponseEntity<List<AuthorDTO>> getAuthors() throws EntityNotFoundException {
        log.info("Getting List of Author");
        return ResponseEntity.ok(authorAdapterUseCase.getAuthors());
    }

    @PostMapping
    @Operation(description = "Save new author.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully save author."),
            @ApiResponse(responseCode = "400", description = "Bad Request. Please check response body for further details."),
            @ApiResponse(responseCode = "404", description = "Could not be saved."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error. Please check response body for further details.")
    })
    public ResponseEntity<String> post(@RequestBody Author author) throws EntityNotFoundException, IllegalArgumentException {
        log.info("Saving Author item.");
        AuthorDTO e = authorAdapterUseCase.insert(author);

        return e != null ? new ResponseEntity<>("Successfully save author.", HttpStatus.OK) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Operation(description = "Update author by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated author."),
            @ApiResponse(responseCode = "400", description = "Bad Request. Please check response body for further details."),
            @ApiResponse(responseCode = "404", description = "Author not found."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error. Please check response body for further details.")
    })
    public ResponseEntity<String> put(@PathVariable("id") Long id, @RequestBody Author author) throws EntityNotFoundException, IllegalArgumentException {
        log.info("Updating Author item.");
        author.setAuthorId(id);
        AuthorDTO e = authorAdapterUseCase.update(author, id);

        return e != null ? new ResponseEntity<>("Successfully update author.", HttpStatus.OK) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Delete author by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted author."),
            @ApiResponse(responseCode = "400", description = "Bad Request. Please check response body for further details."),
            @ApiResponse(responseCode = "404", description = "Author not found."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error. Please check response body for further details.")
    })
    public ResponseEntity<AuthorDTO> delete(@PathVariable("id") Long id) throws EntityNotFoundException, IllegalArgumentException {
        log.info("Deleting Author item.");
        authorAdapterUseCase.delete(id);
        return ResponseEntity.ok().build();
    }
}