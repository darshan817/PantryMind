package com.innogent.pantry_mind.service;

import com.innogent.pantry_mind.dto.requestdto.UnitRequestDTO;
import com.innogent.pantry_mind.dto.responsedto.UnitResponseDTO;
import java.util.List;

public interface UnitService {
    UnitResponseDTO create(UnitRequestDTO unitRequestDTO);
    UnitResponseDTO findById(Long id);
    List<UnitResponseDTO> findAll();
}
