package com.financewebapp.api.controllers;

import com.financewebapp.api.dto.DebitDTO;
import com.financewebapp.api.dto.DebitsDTO;
import com.financewebapp.api.model.Category;
import com.financewebapp.api.model.Debit;
import com.financewebapp.api.service.DebitService;
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
import java.lang.reflect.InvocationTargetException;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/v1/debit")
public class DebitController {

    @Autowired
    private DebitService debitService;
    private static final Logger LOG = LoggerFactory.getLogger(DebitController.class);

    @GetMapping("/list")
    @ApiOperation("Get list of debits.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieve list of debits."),
            @ApiResponse(code = 400, message = "Bad Request. Please check response body for further details."),
            @ApiResponse(code = 404, message = "Debits not found."),
            @ApiResponse(code = 500, message = "Internal Server Error. Please check response body for further details.")
    })
    public ResponseEntity<DebitsDTO> getDebits() throws EntityNotFoundException {
        LOG.info("Getting debits list.");
        return ResponseEntity.ok(debitService.getDebits());
    }

    @GetMapping("/{month}/{year}")
    @ApiOperation("Get list of debits by month and year.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieve list of debits for given month and year."),
            @ApiResponse(code = 400, message = "Bad Request. Please check response body for further details."),
            @ApiResponse(code = 404, message = "Debits not found."),
            @ApiResponse(code = 500, message = "Internal Server Error. Please check response body for further details.")
    })
    public ResponseEntity<DebitsDTO> getDebitsByMonthAndYear(@PathVariable("month") Integer month, @PathVariable("year") Integer year) throws EntityNotFoundException {
        LOG.info("Getting List of Debit By Month And Year");
        return ResponseEntity.ok(debitService.getDebitsByMonthAndYear(month, year));
    }

    @GetMapping("/{month}/{year}/{categoryId}")
    @ApiOperation("Get list of debits by month, year and category.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieve list of debits for given month, year and category."),
            @ApiResponse(code = 400, message = "Bad Request. Please check response body for further details."),
            @ApiResponse(code = 404, message = "Debits not found."),
            @ApiResponse(code = 500, message = "Internal Server Error. Please check response body for further details.")
    })
    public ResponseEntity<DebitsDTO> getDebitsByMonthYearAndCategory(@PathVariable("month") Integer month, @PathVariable("year") Integer year, @PathVariable("categoryId") Long categoryId) throws EntityNotFoundException {
        LOG.info("Getting List of Debit By Month, Year and Category");
        Category category = new Category(categoryId, null);
        return ResponseEntity.ok(debitService.getDebitsByMonthAndYearAndCategory(month, year, category));
    }

    @PostMapping
    @ApiOperation("Save new debit item.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully save debit"),
            @ApiResponse(code = 400, message = "Bad Request. Please check response body for further details."),
            @ApiResponse(code = 404, message = "Could not be saved."),
            @ApiResponse(code = 500, message = "Internal Server Error. Please check response body for further details.")
    })
    public ResponseEntity<String> post(@RequestBody Debit debit) throws EntityNotFoundException, IllegalArgumentException {
        LOG.info("Posting item of Debit");
        Debit e = debitService.insert(debit);

        return e != null ? new ResponseEntity<>("Successfully save debit.", HttpStatus.OK) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @ApiOperation("Update debit by ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully update debit."),
            @ApiResponse(code = 400, message = "Bad Request. Please check response body for further details."),
            @ApiResponse(code = 404, message = "Debit not found."),
            @ApiResponse(code = 500, message = "Internal Server Error. Please check response body for further details.")
    })
    public ResponseEntity<String> put(@PathVariable("id") Long id, @RequestBody Debit debit) throws EntityNotFoundException, IllegalArgumentException {
        LOG.info("Updating debit item.");
        debit.setId(id);
        DebitDTO e = debitService.update(debit, id);

        return e != null ? new ResponseEntity<>("Successfully update debit.", HttpStatus.OK) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete debit by ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully delete debit."),
            @ApiResponse(code = 400, message = "Bad Request. Please check response body for further details."),
            @ApiResponse(code = 404, message = "Debit not found."),
            @ApiResponse(code = 500, message = "Internal Server Error. Please check response body for further details.")
    })
    public ResponseEntity<DebitDTO> delete(@PathVariable("id") Long id) throws EntityNotFoundException, IllegalArgumentException {
        LOG.info("Deleting item of Debit");
        debitService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    @ApiOperation("Partial update debit by ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully update debit partially."),
            @ApiResponse(code = 400, message = "Bad Request. Please check response body for further details."),
            @ApiResponse(code = 404, message = "Debit not found."),
            @ApiResponse(code = 500, message = "Internal Server Error. Please check response body for further details.")
    })
    public ResponseEntity<String> patch(@PathVariable("id") Long id, @RequestBody Debit debit) throws EntityNotFoundException, IllegalArgumentException, InvocationTargetException, IllegalAccessException {
        LOG.info("Patching item of Debit");
        DebitDTO e = debitService.patch(id, debit);

        return e != null ? new ResponseEntity<>("Successfully update debit partially.", HttpStatus.OK) : ResponseEntity.notFound().build();
    }
}
