package com.financewebapp.api.controllers;

import com.financewebapp.api.dto.CategoryDTO;
import com.financewebapp.api.model.Category;
import com.financewebapp.api.service.CategoryService;

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
import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    @Autowired
    private CategoryService service;
    private static final Logger LOG = LoggerFactory.getLogger(CategoryController.class);

    @GetMapping("/list")
    @ApiOperation("Get a list of categories.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list of categories."),
            @ApiResponse(code = 400, message = "Bad Request. Please check response body for further details."),
            @ApiResponse(code = 404, message = "Categories not found."),
            @ApiResponse(code = 500, message = "Internal Server Error. Please check response body for further details.")
    })
    public ResponseEntity<List<CategoryDTO>> getCategories() throws EntityNotFoundException {
        LOG.info("Getting categories list.");
        return ResponseEntity.ok(service.getCategories());
    }

    @PostMapping
    @ApiOperation("Save new category item")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully save new category item."),
            @ApiResponse(code = 400, message = "Bad Request. Please check response body for further details."),
            @ApiResponse(code = 404, message = "Could not be saved."),
            @ApiResponse(code = 500, message = "Internal Server Error. Please check response body for further details.")
    })
    public ResponseEntity<String> post(@RequestBody Category category) throws EntityNotFoundException, IllegalArgumentException {
        LOG.info("Posting item of Category");
        CategoryDTO e = service.insert(category);

        return e != null ? new ResponseEntity<>("Successfully save category.", HttpStatus.OK) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @ApiOperation("Update category by ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated category by ID."),
            @ApiResponse(code = 400, message = "Bad Request. Please check response body for further details."),
            @ApiResponse(code = 404, message = "Category not found."),
            @ApiResponse(code = 500, message = "Internal Server Error. Please check response body for further details.")
    })
    public ResponseEntity<String> put(@PathVariable("id") Long id, @RequestBody Category category) throws EntityNotFoundException, IllegalArgumentException {
        LOG.info("Updating category item.");
        category.setCategoryId(id);
        CategoryDTO e = service.update(category, id);

        return e != null ? new ResponseEntity<>("Successfully update category.", HttpStatus.OK) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete category by ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted category by ID."),
            @ApiResponse(code = 400, message = "Bad Request. Please check response body for further details."),
            @ApiResponse(code = 404, message = "Category not found."),
            @ApiResponse(code = 500, message = "Internal Server Error. Please check response body for further details.")
    })
    public ResponseEntity<CategoryDTO> delete(@PathVariable("id") Long id) throws EntityNotFoundException, IllegalArgumentException {
        LOG.info("Deleting category item.");
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
