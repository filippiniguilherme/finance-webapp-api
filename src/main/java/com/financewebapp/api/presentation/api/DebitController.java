package com.financewebapp.api.presentation.api;

import com.financewebapp.api.application.DebitAdapterUseCase;
import com.financewebapp.api.infrastructure.model.Category;
import com.financewebapp.api.infrastructure.model.Debit;
import com.financewebapp.api.presentation.api.v0.response.DebitDTO;
import com.financewebapp.api.presentation.api.v0.response.DebitsDTO;
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
@RequestMapping("/api/v1/debit")
public class DebitController {

    private final DebitAdapterUseCase debitAdapterUseCase;

    @GetMapping("/list")
    @Operation(description = "Get list of debits.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieve list of debits."),
            @ApiResponse(responseCode = "400", description = "Bad Request. Please check response body for further details."),
            @ApiResponse(responseCode = "404", description = "Debits not found."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error. Please check response body for further details.")
    })
    public ResponseEntity<DebitsDTO> getDebits() throws EntityNotFoundException {
        log.info("Getting debits list.");
        return ResponseEntity.ok(debitAdapterUseCase.getDebits());
    }

    @GetMapping("/{month}/{year}")
    @Operation(description = "Get list of debits by month and year.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieve list of debits for given month and year."),
            @ApiResponse(responseCode = "400", description = "Bad Request. Please check response body for further details."),
            @ApiResponse(responseCode = "404", description = "Debits not found."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error. Please check response body for further details.")
    })
    public ResponseEntity<DebitsDTO> getDebitsByMonthAndYear(@PathVariable("month") Integer month, @PathVariable("year") Integer year) throws EntityNotFoundException {
        log.info("Getting List of Debit By Month And Year");
        return ResponseEntity.ok(debitAdapterUseCase.getDebitsByMonthAndYear(month, year));
    }

    @GetMapping("/{month}/{year}/{categoryId}")
    @Operation(description = "Get list of debits by month, year and category.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieve list of debits for given month, year and category."),
            @ApiResponse(responseCode = "400", description = "Bad Request. Please check response body for further details."),
            @ApiResponse(responseCode = "404", description = "Debits not found."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error. Please check response body for further details.")
    })
    public ResponseEntity<DebitsDTO> getDebitsByMonthYearAndCategory(@PathVariable("month") Integer month, @PathVariable("year") Integer year, @PathVariable("categoryId") Long categoryId) throws EntityNotFoundException {
        log.info("Getting List of Debit By Month, Year and Category");
        Category category = new Category(categoryId, null);
        return ResponseEntity.ok(debitAdapterUseCase.getDebitsByMonthAndYearAndCategory(month, year, category));
    }

    @PostMapping
    @Operation(description = "Save new debit item.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully save debit"),
            @ApiResponse(responseCode = "400", description = "Bad Request. Please check response body for further details."),
            @ApiResponse(responseCode = "404", description = "Could not be saved."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error. Please check response body for further details.")
    })
    public ResponseEntity<String> post(@RequestBody Debit debit) throws EntityNotFoundException, IllegalArgumentException {
        log.info("Posting item of Debit");
        Debit e = debitAdapterUseCase.insert(debit);

        return e != null ? new ResponseEntity<>("Successfully save debit.", HttpStatus.OK) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Operation(description = "Update debit by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully update debit."),
            @ApiResponse(responseCode = "400", description = "Bad Request. Please check response body for further details."),
            @ApiResponse(responseCode = "404", description = "Debit not found."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error. Please check response body for further details.")
    })
    public ResponseEntity<String> put(@PathVariable("id") Long id, @RequestBody Debit debit) throws EntityNotFoundException, IllegalArgumentException {
        log.info("Updating debit item.");
        debit.setId(id);
        DebitDTO e = debitAdapterUseCase.update(debit, id);

        return e != null ? new ResponseEntity<>("Successfully update debit.", HttpStatus.OK) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Delete debit by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully delete debit."),
            @ApiResponse(responseCode = "400", description = "Bad Request. Please check response body for further details."),
            @ApiResponse(responseCode = "404", description = "Debit not found."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error. Please check response body for further details.")
    })
    public ResponseEntity<DebitDTO> delete(@PathVariable("id") Long id) throws EntityNotFoundException, IllegalArgumentException {
        log.info("Deleting item of Debit");
        debitAdapterUseCase.delete(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    @Operation(description = "Partial update debit by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully update debit partially."),
            @ApiResponse(responseCode = "400", description = "Bad Request. Please check response body for further details."),
            @ApiResponse(responseCode = "404", description = "Debit not found."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error. Please check response body for further details.")
    })
    public ResponseEntity<String> patch(@PathVariable("id") Long id, @RequestBody Debit debit) throws EntityNotFoundException, IllegalArgumentException, InvocationTargetException, IllegalAccessException {
        log.info("Patching item of Debit");
        DebitDTO e = debitAdapterUseCase.patch(id, debit);

        return e != null ? new ResponseEntity<>("Successfully update debit partially.", HttpStatus.OK) : ResponseEntity.notFound().build();
    }
}