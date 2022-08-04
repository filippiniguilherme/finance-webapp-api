package com.financewebapp.api.controllers;

import com.financewebapp.api.dto.DebitDTO;
import com.financewebapp.api.model.Debit;
import com.financewebapp.api.service.DebitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/debit")
public class DebitController {

    @Autowired
    private DebitService debitService;

    @GetMapping("/list")
    public ResponseEntity<List<DebitDTO>> getDebits() {
        return ResponseEntity.ok(debitService.getDebits());
    }

    @PostMapping
    public ResponseEntity insertDebit(@RequestBody Debit debit) {
        DebitDTO debitDto = debitService.insert(debit);
        return ResponseEntity.created(null).build();
    }
}
