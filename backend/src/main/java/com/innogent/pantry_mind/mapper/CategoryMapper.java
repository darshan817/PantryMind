package com.innogent.pantry_mind.mapper;

import com.innogent.pantry_mind.dto.requestdto.CategoryRequestDTO;
import com.innogent.pantry_mind.dto.responsedto.CategoryResponseDTO;
import com.innogent.pantry_mind.entity.Category;

public class CategoryMapper {
    public CategoryResponseDTO toResponseDto(Category category) {
        CategoryResponseDTO dto = new CategoryResponseDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        return dto;
    }
    
    public Category toEntity(CategoryRequestDTO categoryRequestDTO) {
        Category category = new Category();
        category.setName(categoryRequestDTO.getName());
        category.setDescription(categoryRequestDTO.getDescription());
        return category;
    }
}
