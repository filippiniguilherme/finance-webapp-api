package com.financewebapp.api.controllers;

import com.financewebapp.api.dto.BalanceDTO;
import com.financewebapp.api.service.BalanceService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/balance")
public class BalanceController {

    @Autowired
    private BalanceService balanceService;
    private static final Logger LOG = LoggerFactory.getLogger(BalanceController.class);

    @GetMapping("/{month}/{year}")
    public ResponseEntity<BalanceDTO> getBalance(@PathVariable("month") Integer month, @PathVariable("year") Integer year) {
        LOG.info("Getting List of Balance");
        return ResponseEntity.ok(balanceService.getBalance(month, year));
    }
}
