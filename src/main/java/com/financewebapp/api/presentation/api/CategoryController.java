package com.financewebapp.api.presentation.api;

import com.financewebapp.api.application.CategoryAdapterUseCase;
import com.financewebapp.api.infrastructure.model.Category;
import com.financewebapp.api.presentation.api.v0.response.CategoryDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@CrossOrigin(maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryAdapterUseCase categoryAdapterUseCase;

    @GetMapping("/list")
    @Operation(description = "Get a list of categories.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of categories."),
            @ApiResponse(responseCode = "400", description = "Bad Request. Please check response body for further details."),
            @ApiResponse(responseCode = "404", description = "Categories not found."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error. Please check response body for further details.")
    })
    public ResponseEntity<List<CategoryDTO>> getCategories() throws EntityNotFoundException {
        log.info("Getting categories list.");
        return ResponseEntity.ok(categoryAdapterUseCase.getCategories());
    }

    @PostMapping
    @Operation(description = "Save new category item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully save new category item."),
            @ApiResponse(responseCode = "400", description = "Bad Request. Please check response body for further details."),
            @ApiResponse(responseCode = "404", description = "Could not be saved."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error. Please check response body for further details.")
    })
    public ResponseEntity<String> post(@RequestBody Category category) throws EntityNotFoundException, IllegalArgumentException {
        log.info("Posting item of Category");
        CategoryDTO e = categoryAdapterUseCase.insert(category);

        return e != null ? new ResponseEntity<>("Successfully save category.", HttpStatus.OK) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Operation(description = "Update category by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated category by ID."),
            @ApiResponse(responseCode = "400", description = "Bad Request. Please check response body for further details."),
            @ApiResponse(responseCode = "404", description = "Category not found."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error. Please check response body for further details.")
    })
    public ResponseEntity<String> put(@PathVariable("id") Long id, @RequestBody Category category) throws EntityNotFoundException, IllegalArgumentException {
        log.info("Updating category item.");
        category.setCategoryId(id);
        CategoryDTO e = categoryAdapterUseCase.update(category, id);

        return e != null ? new ResponseEntity<>("Successfully update category.", HttpStatus.OK) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Delete category by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted category by ID."),
            @ApiResponse(responseCode = "400", description = "Bad Request. Please check response body for further details."),
            @ApiResponse(responseCode = "404", description = "Category not found."),
            @ApiResponse(responseCode = "500", description = "Internal Server Error. Please check response body for further details.")
    })
    public ResponseEntity<CategoryDTO> delete(@PathVariable("id") Long id) throws EntityNotFoundException, IllegalArgumentException {
        log.info("Deleting category item.");
        categoryAdapterUseCase.delete(id);
        return ResponseEntity.ok().build();
    }
}