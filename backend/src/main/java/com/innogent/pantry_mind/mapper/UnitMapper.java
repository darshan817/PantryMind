package com.innogent.pantry_mind.mapper;

import com.innogent.pantry_mind.dto.requestdto.UnitRequestDTO;
import com.innogent.pantry_mind.dto.responsedto.UnitResponseDTO;
import com.innogent.pantry_mind.entity.Unit;

public class UnitMapper {
    public UnitResponseDTO toResponseDto(Unit unit) {
        UnitResponseDTO responseDto = new UnitResponseDTO();
        responseDto.setId(unit.getId());
        responseDto.setName(unit.getName());
        responseDto.setType(unit.getType());
        return responseDto;
    }

    public Unit toEntity(UnitRequestDTO unitRequestDTO) {
        Unit unit = new Unit();
        unit.setName(unitRequestDTO.getName());
        unit.setType(unitRequestDTO.getType());
        return unit;
    }
}
