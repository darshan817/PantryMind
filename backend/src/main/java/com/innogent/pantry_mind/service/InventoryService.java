package com.innogent.pantry_mind.service;

import com.innogent.pantry_mind.dto.CreateInventoryItemDTO;
import com.innogent.pantry_mind.dto.InventoryItemDTO;
import com.innogent.pantry_mind.dto.UpdateInventoryItemDTO;

import java.util.List;

public interface InventoryService {

    InventoryItemDTO addInventoryItem(CreateInventoryItemDTO dto);

    List<InventoryItemDTO> getAllInventoryItems();

    InventoryItemDTO getInventoryItemById(Long id);

    InventoryItemDTO updateInventoryItem(UpdateInventoryItemDTO dto, Long id);

    void deleteInventoryItem(Long id);
}
