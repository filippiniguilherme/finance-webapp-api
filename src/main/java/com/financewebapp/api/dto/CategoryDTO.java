package com.financewebapp.api.dto;

import com.financewebapp.api.model.Category;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class CategoryDTO {
    private Long CategoryId;
    private String CategoryName;

    public static CategoryDTO create(Category category) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(category, CategoryDTO.class);
    }
}
