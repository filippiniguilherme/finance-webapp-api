package com.financewebapp.api.controllers;

import com.financewebapp.api.dto.EntryDTO;
import com.financewebapp.api.model.Entry;
import com.financewebapp.api.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/entry")
public class EntryController {
    @Autowired
    private EntryService service;

    @GetMapping("/list")
    public ResponseEntity<List<EntryDTO>> getEntries() {
        return ResponseEntity.ok(service.getEntries());
    }

    @PostMapping
    public ResponseEntity<EntryDTO> post(@RequestBody Entry entry) {
        EntryDTO e = service.insert(entry);
        return ResponseEntity.created(null).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntryDTO> put(@PathVariable("id") Long id, @RequestBody Entry entry) {
        entry.setEntryId(id);
        EntryDTO e = service.update(entry, id);

        return e != null ? ResponseEntity.ok(e) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EntryDTO> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EntryDTO> patch(@PathVariable("id") Long id, @RequestBody Entry entry) {
        EntryDTO e = service.patch(id, entry);

        return e != null ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
