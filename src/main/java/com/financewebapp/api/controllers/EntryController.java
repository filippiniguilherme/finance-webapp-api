package com.financewebapp.api.controllers;

import com.financewebapp.api.dto.EntryDTO;
import com.financewebapp.api.model.Entry;
import com.financewebapp.api.service.EntryService;

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

@RestController
@RequestMapping("/api/v1/entry")
public class EntryController {
    @Autowired
    private EntryService entryService;
    private static final Logger LOG = LoggerFactory.getLogger(EntryController.class);

    @GetMapping("/list")
    @ApiOperation("Get all entries")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieve entries."),
            @ApiResponse(code = 400, message = "Bad Request. Please check response body for further details."),
            @ApiResponse(code = 404, message = "Entries not found."),
            @ApiResponse(code = 500, message = "Internal Server Error. Please check response body for further details.")
    })
    public ResponseEntity<List<EntryDTO>> getEntries() throws EntityNotFoundException {
        LOG.info("Getting list of entries.");
        return ResponseEntity.ok(entryService.getEntries());
    }

    @GetMapping("/{month}/{year}")
    @ApiOperation("Get entries filtered by month and year.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieve entries for this month and year."),
            @ApiResponse(code = 400, message = "Bad Request. Please check response body for further details."),
            @ApiResponse(code = 404, message = "Entries not found."),
            @ApiResponse(code = 500, message = "Internal Server Error. Please check response body for further details.")
    })
    public ResponseEntity<List<Entry>> getEntrysByMonthAndYear(@PathVariable("month") Integer month, @PathVariable("year") Integer year) throws EntityNotFoundException {
        LOG.info("Getting list of entries by month and year.");
        return ResponseEntity.ok(entryService.getEntriesByMonthAndYear(month, year));
    }

    @GetMapping("/{month}/{year}/{categoryId}")
    @ApiOperation("Get entries filtered by month, year and category.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieve entries for this month and year."),
            @ApiResponse(code = 400, message = "Bad Request. Please check response body for further details."),
            @ApiResponse(code = 404, message = "Entries not found."),
            @ApiResponse(code = 500, message = "Internal Server Error. Please check response body for further details.")
    })
    public ResponseEntity<List<Entry>> getEntrysByMonthAndYearAndCategoryId(@PathVariable("month") Integer month, @PathVariable("year") Integer year, @PathVariable("categoryId") Long categoryId) throws EntityNotFoundException {
    LOG.info("Getting list of entries by month, year and category");
        return ResponseEntity.ok(entryService.getEntriesByMonthAndYearAndCategory(month, year, categoryId));
    }

    @PostMapping
    @ApiOperation("Save a new entry")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully save entry."),
            @ApiResponse(code = 400, message = "Bad Request. Please check response body for further details."),
            @ApiResponse(code = 404, message = "Could not be saved."),
            @ApiResponse(code = 500, message = "Internal Server Error. Please check response body for further details.")
    })
    public ResponseEntity<String> post(@RequestBody Entry entry) throws EntityNotFoundException, IllegalArgumentException {
        LOG.info("Saving new Entry item.");
        EntryDTO e = entryService.insert(entry);

        return e != null ? new ResponseEntity<>("Successfully save entry.", HttpStatus.OK) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @ApiOperation("Update entry by id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully update entry."),
            @ApiResponse(code = 400, message = "Bad Request. Please check response body for further details."),
            @ApiResponse(code = 404, message = "Could not be updated."),
            @ApiResponse(code = 500, message = "Internal Server Error. Please check response body for further details.")
    })
    public ResponseEntity<String> put(@PathVariable("id") Long id, @RequestBody Entry entry) throws EntityNotFoundException, IllegalArgumentException {
        LOG.info("Updating Entry item by ID.");
        entry.setEntryId(id);
        EntryDTO e = entryService.update(entry, id);

        return e != null ? new ResponseEntity<>("Successfully update entry.", HttpStatus.OK) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete entry by id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully delete entry."),
            @ApiResponse(code = 400, message = "Bad Request. Please check response body for further details."),
            @ApiResponse(code = 404, message = "Could not be deleted."),
            @ApiResponse(code = 500, message = "Internal Server Error. Please check response body for further details.")
    })
    public ResponseEntity<EntryDTO> delete(@PathVariable("id") Long id) throws EntityNotFoundException, IllegalArgumentException {
        LOG.info("Deleting Entry item by ID.");
        entryService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    @ApiOperation("Partial update entry by id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully update entry partially."),
            @ApiResponse(code = 400, message = "Bad Request. Please check response body for further details."),
            @ApiResponse(code = 404, message = "Could not be updated."),
            @ApiResponse(code = 500, message = "Internal Server Error. Please check response body for further details.")
    })
    public ResponseEntity<String> patch(@PathVariable("id") Long id, @RequestBody Entry entry) throws EntityNotFoundException, IllegalArgumentException {
        LOG.info("Updating Entry item partially.");
        EntryDTO e = entryService.patch(id, entry);

        return e != null ? new ResponseEntity<>("Successfully update entry partially.", HttpStatus.OK) : ResponseEntity.notFound().build();
    }
}
