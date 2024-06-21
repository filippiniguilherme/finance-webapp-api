package com.financewebapp.api.infrastructure;

import com.financewebapp.api.domain.CategoryRepositoryService;
import com.financewebapp.api.infrastructure.model.Category;
import com.financewebapp.api.infrastructure.repository.CategoryRepository;
import com.financewebapp.api.presentation.api.v0.response.CategoryDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.internal.util.Assert;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryRepositoryAdapter implements CategoryRepositoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryDTO> getCategories() {
        log.info("List Categories");
        return categoryRepository.findAll().stream().map(CategoryDTO::create).collect(Collectors.toList());
    };

    public CategoryDTO insert(Category category) {
        log.info("Insert Category: {}", category.getCategoryName());
        return CategoryDTO.create(categoryRepository.save(category));
    }

    public CategoryDTO update(Category category, Long id) {
        Assert.notNull(id, "Unable to update the data");

        Optional<Category> optional = categoryRepository.findById(id);
        if(optional.isPresent()){
            Category db = optional.get();

            db.setCategoryName(category.getCategoryName());

            log.info("Update Category: {}", db.getCategoryId());

            categoryRepository.save(db);
            return CategoryDTO.create(db);
        } else {
            throw new RuntimeException("Unable to update the data");
        }
    }

    public void delete(Long id) {
        log.info("Delete Category: {}", id);
        categoryRepository.deleteById(id);
    }
}