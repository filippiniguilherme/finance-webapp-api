package com.financewebapp.api.controllers;

import com.financewebapp.api.dto.CategoryDTO;
import com.financewebapp.api.model.Category;
import com.financewebapp.api.service.CategoryService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    @Autowired
    private CategoryService service;
    private static final Logger LOG = LoggerFactory.getLogger(CategoryController.class);

    @GetMapping("/list")
    public ResponseEntity<List<CategoryDTO>> getCategorys() {
        LOG.info("Getting List of Category");
        return ResponseEntity.ok(service.getCategorys());
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> post(@RequestBody Category category) {
        LOG.info("Posting item of Author");
        CategoryDTO e = service.insert(category);

        return e != null ? ResponseEntity.created(null).build() : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> put(@PathVariable("id") Long id, @RequestBody Category category) {
        LOG.info("Puting item of Category");
        category.setCategoryId(id);
        CategoryDTO e = service.update(category, id);

        return e != null ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryDTO> delete(@PathVariable("id") Long id) {
        LOG.info("Deleting item of Category");
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
