package com.financewebapp.api.service;

import com.financewebapp.api.dto.CategoryDTO;
import com.financewebapp.api.model.Category;
import com.financewebapp.api.repository.CategoryRepository;
import org.modelmapper.internal.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    private static final Logger LOG = LoggerFactory.getLogger(CategoryService.class);

    public List<CategoryDTO> getCategories() {
        LOG.info("List Categories");
        return categoryRepository.findAll().stream().map(CategoryDTO::create).collect(Collectors.toList());
    };

    public CategoryDTO insert(Category category) {
        LOG.info("Insert Category: {}", category.getCategoryName());
        return CategoryDTO.create(categoryRepository.save(category));
    }

    public CategoryDTO update(Category category, Long id) {
        Assert.notNull(id, "Unable to update the data");

        Optional<Category> optional = categoryRepository.findById(id);
        if(optional.isPresent()){
            Category db = optional.get();

            db.setCategoryName(category.getCategoryName());

            LOG.info("Update Category: {}", db.getCategoryId());

            categoryRepository.save(db);
            return CategoryDTO.create(db);
        } else {
            throw new RuntimeException("Unable to update the data");
        }
    }

    public void delete(Long id) {
        LOG.info("Delete Category: {}", id);
        categoryRepository.deleteById(id);
    }
}
