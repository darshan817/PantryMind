package com.innogent.pantry_mind.service;

import com.innogent.pantry_mind.dto.requestdto.CategoryRequestDTO;
import com.innogent.pantry_mind.dto.responsedto.CategoryResponseDTO;
import java.util.List;

public interface CategoryService {
    CategoryResponseDTO create(CategoryRequestDTO categoryRequestDTO);
    CategoryResponseDTO findById(Long id);
    List<CategoryResponseDTO> findAll();
}
