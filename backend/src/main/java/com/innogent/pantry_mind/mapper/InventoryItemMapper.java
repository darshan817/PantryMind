package com.innogent.pantry_mind.mapper;

import com.innogent.pantry_mind.dto.CreateInventoryItemDTO;
import com.innogent.pantry_mind.dto.InventoryItemDTO;
import com.innogent.pantry_mind.dto.UpdateInventoryItemDTO;
import com.innogent.pantry_mind.entity.InventoryItem;
import org.springframework.stereotype.Component;

@Component
public class InventoryItemMapper {

    public InventoryItemDTO toDTO(InventoryItem entity) {
        InventoryItemDTO dto = new InventoryItemDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setCategory_id(entity.getCategory_id());
        dto.setUnit_id(entity.getUnit_id());
        dto.setCreated_by(entity.getCreated_by());
        dto.setQuantity(entity.getQuantity());
        dto.setLocation(entity.getLocation());
        dto.setExpiryDate(entity.getExpiryDate());
        dto.setCreated_at(entity.getCreated_at());
        return dto;
    }

    public InventoryItem toEntity(CreateInventoryItemDTO dto) {
        InventoryItem entity = new InventoryItem();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setCategory_id(dto.getCategory_id());
        entity.setUnit_id(dto.getUnit_id());
        entity.setQuantity(dto.getQuantity());
        entity.setLocation(dto.getLocation());
        entity.setExpiryDate(dto.getExpiryDate());
        return entity;
    }

    public void updateEntity(InventoryItem entity, UpdateInventoryItemDTO dto) {
        if (dto.getName() != null) entity.setName(dto.getName());
        if (dto.getDescription() != null) entity.setDescription(dto.getDescription());
        if (dto.getCategory_id() != null) entity.setCategory_id(dto.getCategory_id());
        if (dto.getUnit_id() != null) entity.setUnit_id(dto.getUnit_id());
        if (dto.getQuantity() != null) entity.setQuantity(dto.getQuantity());
        if (dto.getLocation() != null) entity.setLocation(dto.getLocation());
        if (dto.getExpiryDate() != null) entity.setExpiryDate(dto.getExpiryDate());
    }
}
