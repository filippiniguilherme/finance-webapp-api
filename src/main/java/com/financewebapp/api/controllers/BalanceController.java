package com.financewebapp.api.controllers;

import com.financewebapp.api.dto.BalanceDTO;
import com.financewebapp.api.service.BalanceService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/v1/balance")
public class BalanceController {

    @Autowired
    private BalanceService balanceService;
    private static final Logger LOG = LoggerFactory.getLogger(BalanceController.class);

    @GetMapping("/")
    @ApiOperation("Get balance total.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved balance total."),
            @ApiResponse(code = 400, message = "Bad Request. Please check response body for further details."),
            @ApiResponse(code = 404, message = "Balance not found."),
            @ApiResponse(code = 500, message = "Internal Server Error. Please check response body for further details.")
    })
    public ResponseEntity<BalanceDTO> getBalance() throws EntityNotFoundException {
        LOG.info("Getting List of Balance");
        return ResponseEntity.ok(balanceService.getBalance());
    }

    @GetMapping("/{month}/{year}")
    @ApiOperation("Get balance by month and year.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved balance for given month and year."),
            @ApiResponse(code = 400, message = "Bad Request. Please check response body for further details."),
            @ApiResponse(code = 404, message = "Balance not found."),
            @ApiResponse(code = 500, message = "Internal Server Error. Please check response body for further details.")
    })
    public ResponseEntity<BalanceDTO> getBalanceByMonthAndYear(
            @PathVariable("month") Integer month, @PathVariable("year") Integer year
    ) throws EntityNotFoundException {
        LOG.info("Getting List of Balance");
        return ResponseEntity.ok(balanceService.getBalanceByMonthAndYear(month, year));
    }
}
