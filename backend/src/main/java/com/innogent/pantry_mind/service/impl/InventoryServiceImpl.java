package com.innogent.pantry_mind.service.impl;

import com.innogent.pantry_mind.dto.request.CreateInventoryItemRequestDTO;
import com.innogent.pantry_mind.dto.response.InventoryItemResponseDTO;
import com.innogent.pantry_mind.dto.request.UpdateInventoryItemRequestDTO;
import com.innogent.pantry_mind.entity.InventoryItem;
import com.innogent.pantry_mind.entity.User;
import com.innogent.pantry_mind.mapper.InventoryItemMapper;
import com.innogent.pantry_mind.repository.InventoryItemRepository;
import com.innogent.pantry_mind.repository.UserRepository;
import com.innogent.pantry_mind.service.InventoryService;
import com.innogent.pantry_mind.exception.ItemNotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryItemRepository inventoryItemRepository;
    private final InventoryItemMapper mapper;
    private final UserRepository userRepository;

    @Override
    public InventoryItemResponseDTO addInventoryItem(CreateInventoryItemRequestDTO dto) {
        InventoryItem entity = mapper.toEntity(dto);
        InventoryItem saved = inventoryItemRepository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    public List<InventoryItemResponseDTO> getAllInventoryItems() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("Fetching inventory for username: {}", username);
        
        User user = userRepository.findByUsernameWithKitchen(username).orElse(null);
        
        if (user != null) {
            log.info("User found: {}, Kitchen: {}", user.getId(), user.getKitchen() != null ? user.getKitchen().getId() : "null");
            if (user.getKitchen() != null) {
                Long kitchenId = user.getKitchen().getId();
                log.info("Searching inventory items for kitchen ID: {}", kitchenId);
                List<InventoryItem> items = inventoryItemRepository.findByKitchenId(kitchenId);
                log.info("Found {} items for kitchen {}", items.size(), kitchenId);
                return items.stream()
                        .map(mapper::toResponse)
                        .collect(Collectors.toList());
            }
        } else {
            log.warn("User not found for username: {}", username);
        }
        
        log.info("Returning all inventory items");
        return inventoryItemRepository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public InventoryItemResponseDTO getInventoryItemById(Long id) {
        return inventoryItemRepository.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new ItemNotFoundException(id));
    }

    @Override
    public InventoryItemResponseDTO updateInventoryItem(UpdateInventoryItemRequestDTO dto, Long id) {
        InventoryItem entity = inventoryItemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(id));

        mapper.updateEntity(entity, dto);

        InventoryItem updated = inventoryItemRepository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void deleteInventoryItem(Long id) {
        if (!inventoryItemRepository.existsById(id)) {
            throw new ItemNotFoundException(id);
        }
        inventoryItemRepository.deleteById(id);
    }
}