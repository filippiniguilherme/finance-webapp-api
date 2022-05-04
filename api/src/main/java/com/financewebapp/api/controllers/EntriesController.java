package com.financewebapp.api.controllers;

import com.financewebapp.api.dto.EntriesDTO;
import com.financewebapp.api.model.Entries;
import com.financewebapp.api.service.EntriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity post(@RequestBody Entries entries) {
        try{
            EntriesDTO e = service.insert(entries);
            return ResponseEntity.created(null).build();
        } catch(Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable("id") Long id, @RequestBody Entries entries) {
        entries.setId(id);
        EntriesDTO e = service.update(entries, id);

        return e != null ? ResponseEntity.ok(e) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        boolean success = service.delete(id);

        return success ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
