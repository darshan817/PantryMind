package com.innogent.pantry_mind.mapper;

import com.innogent.pantry_mind.dto.request.KitchenRequestDTO;
import com.innogent.pantry_mind.dto.response.KitchenResponseDTO;
import com.innogent.pantry_mind.entity.Kitchen;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface KitchenMapper {
    KitchenResponseDTO toResponseDTO(Kitchen kitchen);
    Kitchen toEntity(KitchenRequestDTO requestDTO);
}
