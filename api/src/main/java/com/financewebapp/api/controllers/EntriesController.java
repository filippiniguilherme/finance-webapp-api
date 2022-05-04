package com.financewebapp.api.controllers;

import com.financewebapp.api.dto.EntriesDTO;
import com.financewebapp.api.model.Entries;
import com.financewebapp.api.service.EntriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/entries")
public class EntriesController {
    @Autowired
    private EntriesService service;

    @GetMapping
    public ResponseEntity<List<EntriesDTO>> getEntries() {
        return ResponseEntity.ok(service.getEntries());
    }
}
