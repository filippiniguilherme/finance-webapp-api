package com.financewebapp.api.dto;

import com.financewebapp.api.model.Category;
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
