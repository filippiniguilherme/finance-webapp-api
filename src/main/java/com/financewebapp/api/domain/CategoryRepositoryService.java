package com.financewebapp.api.domain;


import com.financewebapp.api.infrastructure.model.Category;
import com.financewebapp.api.presentation.api.v0.response.CategoryDTO;

import java.util.List;

public interface CategoryRepositoryService {

    List<CategoryDTO> getCategories();

    CategoryDTO insert(Category category);

    CategoryDTO update(Category category, Long id);

    void delete(Long id);

}