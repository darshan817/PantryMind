package com.innogent.pantry_mind.service.impl;

import com.innogent.pantry_mind.dto.CreateInventoryItemDTO;
import com.innogent.pantry_mind.dto.InventoryItemDTO;
import com.innogent.pantry_mind.dto.UpdateInventoryItemDTO;
import com.innogent.pantry_mind.entity.InventoryItem;
import com.innogent.pantry_mind.mapper.InventoryItemMapper;
import com.innogent.pantry_mind.repository.InventoryItemRepository;
import com.innogent.pantry_mind.service.InventoryService;
import com.innogent.pantry_mind.exception.ItemNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryItemRepository inventoryItemRepository;
    private final InventoryItemMapper mapper;

    @Override
    public InventoryItemDTO addInventoryItem(CreateInventoryItemDTO dto) {
        InventoryItem entity = mapper.toEntity(dto);
        InventoryItem saved = inventoryItemRepository.save(entity);
        return mapper.toDTO(saved);
    }

    @Override
    public List<InventoryItemDTO> getAllInventoryItems() {
        return inventoryItemRepository.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public InventoryItemDTO getInventoryItemById(Long id) {
        return inventoryItemRepository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new ItemNotFoundException(id));
    }

    @Override
    public InventoryItemDTO updateInventoryItem(UpdateInventoryItemDTO dto, Long id) {
        InventoryItem entity = inventoryItemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(id));

        mapper.updateEntity(entity, dto);

        InventoryItem updated = inventoryItemRepository.save(entity);
        return mapper.toDTO(updated);
    }

    @Override
    public void deleteInventoryItem(Long id) {
        if (!inventoryItemRepository.existsById(id)) {
            throw new ItemNotFoundException(id);
        }
        inventoryItemRepository.deleteById(id);
    }
}