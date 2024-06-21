package com.financewebapp.api.presentation.api;

import com.financewebapp.api.application.EntryAdapterUseCase;
import com.financewebapp.api.infrastructure.model.Category;
import com.financewebapp.api.infrastructure.model.Entry;
import com.financewebapp.api.presentation.api.v0.response.EntriesDTO;
import com.financewebapp.api.presentation.api.v0.response.EntryDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;

@Slf4j
@CrossOrigin(maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/entry")
public class EntryController {

    private final EntryAdapterUseCase entryAdapterUseCase;

    @GetMapping("/list")
    @Operation(description = "Get all entries")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieve entries."),
            @ApiResponse(responseCode = "400", description = "Bad Request. Please check response body for further details."),
            @ApiResponse(responseCode = "404", description = "Entries not found."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error. Please check response body for further details.")
    })
    public ResponseEntity<EntriesDTO> getEntries() throws EntityNotFoundException {
        log.info("Getting list of entries.");
        return ResponseEntity.ok(entryAdapterUseCase.getEntries());
    }

    @GetMapping("/{month}/{year}")
    @Operation(description = "Get entries filtered by month and year.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieve entries for this month and year."),
            @ApiResponse(responseCode = "400", description = "Bad Request. Please check response body for further details."),
            @ApiResponse(responseCode = "404", description = "Entries not found."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error. Please check response body for further details.")
    })
    public ResponseEntity<EntriesDTO> getEntrysByMonthAndYear(@PathVariable("month") Integer month, @PathVariable("year") Integer year) throws EntityNotFoundException {
        log.info("Getting list of entries by month and year.");
        return ResponseEntity.ok(entryAdapterUseCase.getEntriesByMonthAndYear(month, year));
    }

    @GetMapping("/{month}/{year}/{categoryId}")
    @Operation(description = "Get entries filtered by month, year and category.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieve entries for this month and year."),
            @ApiResponse(responseCode = "400", description = "Bad Request. Please check response body for further details."),
            @ApiResponse(responseCode = "404", description = "Entries not found."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error. Please check response body for further details.")
    })
    public ResponseEntity<EntriesDTO> getEntrysByMonthAndYearAndCategoryId(@PathVariable("month") Integer month, @PathVariable("year") Integer year, @PathVariable("categoryId") Long categoryId) throws EntityNotFoundException {
        log.info("Getting list of entries by month, year and category");
        Category category = new Category(categoryId, null);
        return ResponseEntity.ok(entryAdapterUseCase.getEntriesByMonthAndYearAndCategory(month, year, category));
    }

    @PostMapping
    @Operation(description = "Save a new entry")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully save entry."),
            @ApiResponse(responseCode = "400", description = "Bad Request. Please check response body for further details."),
            @ApiResponse(responseCode = "404", description = "Could not be saved."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error. Please check response body for further details.")
    })
    public ResponseEntity<String> post(@RequestBody Entry entry) throws EntityNotFoundException, IllegalArgumentException {
        log.info("Saving new Entry item.");
        Entry e = entryAdapterUseCase.insert(entry);

        return e != null ? new ResponseEntity<>("Successfully save entry.", HttpStatus.OK) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Operation(description = "Update entry by id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully update entry."),
            @ApiResponse(responseCode = "400", description = "Bad Request. Please check response body for further details."),
            @ApiResponse(responseCode = "404", description = "Could not be updated."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error. Please check response body for further details.")
    })
    public ResponseEntity<String> put(@PathVariable("id") Long id, @RequestBody Entry entry) throws EntityNotFoundException, IllegalArgumentException {
        log.info("Updating Entry item by ID.");
        entry.setId(id);
        EntryDTO e = entryAdapterUseCase.update(entry, id);

        return e != null ? new ResponseEntity<>("Successfully update entry.", HttpStatus.OK) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Delete entry by id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully delete entry."),
            @ApiResponse(responseCode = "400", description = "Bad Request. Please check response body for further details."),
            @ApiResponse(responseCode = "404", description = "Could not be deleted."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error. Please check response body for further details.")
    })
    public ResponseEntity<EntryDTO> delete(@PathVariable("id") Long id) throws EntityNotFoundException, IllegalArgumentException {
        log.info("Deleting Entry item by ID.");
        entryAdapterUseCase.delete(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    @Operation(description = "Partial update entry by id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully update entry partially."),
            @ApiResponse(responseCode = "400", description = "Bad Request. Please check response body for further details."),
            @ApiResponse(responseCode = "404", description = "Could not be updated."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error. Please check response body for further details.")
    })
    public ResponseEntity<String> patch(@PathVariable("id") Long id, @RequestBody Entry entry) throws EntityNotFoundException, IllegalArgumentException, InvocationTargetException, IllegalAccessException {
        log.info("Updating Entry item partially.");
        entry.setId(id);
        EntryDTO e = entryAdapterUseCase.patch(entry);

        return e != null ? new ResponseEntity<>("Successfully update entry partially.", HttpStatus.OK) : ResponseEntity.notFound().build();
    }
}