package com.financewebapp.api.controllers;

import com.financewebapp.api.dto.DebitDTO;
import com.financewebapp.api.model.Debit;
import com.financewebapp.api.service.DebitService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/debit")
public class DebitController {

    @Autowired
    private DebitService debitService;
    private static final Logger LOG = LoggerFactory.getLogger(CategoryController.class);

    @GetMapping("/list")
    public ResponseEntity<List<DebitDTO>> getDebits() {
        LOG.info("Getting List of Debit");
        return ResponseEntity.ok(debitService.getDebits());
    }

    @GetMapping("/{month}/{year}")
    public ResponseEntity<List<Debit>> getDebitsByMonthAndYear(@PathVariable("month") Integer month, @PathVariable("year") Integer year) {
        LOG.info("Getting List of Debit By Month And Year");
        return ResponseEntity.ok(debitService.getDebitsByMonthAndYear(month, year));
    }

    @PostMapping
    public ResponseEntity<DebitDTO> post(@RequestBody Debit debit) {
        LOG.info("Posting item of Debit");
        DebitDTO e = debitService.insert(debit);

        return e != null ? ResponseEntity.created(null).build() : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<DebitDTO> put(@PathVariable("id") Long id, @RequestBody Debit debit) {
        LOG.info("Puting item of Debit");
        debit.setDebitId(id);
        DebitDTO e = debitService.update(debit, id);

        return e != null ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DebitDTO> delete(@PathVariable("id") Long id) {
        LOG.info("Deleting item of Debit");
        debitService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DebitDTO> patch(@PathVariable("id") Long id, @RequestBody Debit debit) {
        LOG.info("Patching item of Debit");
        DebitDTO e = debitService.patch(id, debit);

        return e != null ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
