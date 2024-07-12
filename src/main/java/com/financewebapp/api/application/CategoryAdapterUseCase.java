package com.financewebapp.api.application;

import com.financewebapp.api.domain.CategoryRepositoryService;
import com.financewebapp.api.infrastructure.model.Category;
import com.financewebapp.api.presentation.api.v0.response.CategoryDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryAdapterUseCase implements CategoryUseCase {

    private final CategoryRepositoryService categoryRepositoryService;

    public List<CategoryDTO> getCategories() {
        return categoryRepositoryService.getCategories();
    }

    public CategoryDTO insert(Category category) {
        return categoryRepositoryService.insert(category);
    }

    public CategoryDTO update(Category category, Long id) {
        return categoryRepositoryService.update(category, id);
    }

    public void delete(Long id) {
        categoryRepositoryService.delete(id);
    }
}