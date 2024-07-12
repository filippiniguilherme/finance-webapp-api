package com.financewebapp.api.presentation.api;

import com.financewebapp.api.application.BalanceAdapterUseCase;
import com.financewebapp.api.presentation.api.v0.response.BalanceDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin(maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/balance")
public class BalanceController {

    private final BalanceAdapterUseCase balanceAdapterUseCase;

    @GetMapping("/")
    @Operation(description = "Get balance total.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved balance total."),
            @ApiResponse(responseCode = "400", description = "Bad Request. Please check response body for further details."),
            @ApiResponse(responseCode = "404", description = "Balance not found."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error. Please check response body for further details.")
    })
    public ResponseEntity<BalanceDTO> getBalance() throws EntityNotFoundException {
        log.info("Getting List of Balance");
        return ResponseEntity.ok(balanceAdapterUseCase.getBalance());
    }

    @GetMapping("/{month}/{year}")
    @Operation(description = "Get balance by month and year.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved balance for given month and year."),
            @ApiResponse(responseCode = "400", description = "Bad Request. Please check response body for further details."),
            @ApiResponse(responseCode = "404", description = "Balance not found."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error. Please check response body for further details.")
    })
    public ResponseEntity<BalanceDTO> getBalanceByMonthAndYear(
            @PathVariable("month") Integer month, @PathVariable("year") Integer year
    ) throws EntityNotFoundException {
        log.info("Getting List of Balance");
        return ResponseEntity.ok(balanceAdapterUseCase.getBalanceByMonthAndYear(month, year));
    }
}