package com.financewebapp.api.presentation.api.v0.response;

import com.financewebapp.api.infrastructure.model.Category;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class CategoryDTO {
    private Long categoryId;
    private String categoryName;

    public static CategoryDTO create(Category category) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(category, CategoryDTO.class);
    }
}
