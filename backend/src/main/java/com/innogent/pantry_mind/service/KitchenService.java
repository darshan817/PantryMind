package com.innogent.pantry_mind.service;

import com.innogent.pantry_mind.dto.request.KitchenRequestDTO;
import com.innogent.pantry_mind.dto.response.KitchenResponseDTO;
import java.util.List;

public interface KitchenService {
    KitchenResponseDTO create(KitchenRequestDTO requestDTO);
    KitchenResponseDTO getById(Long id);
    List<KitchenResponseDTO> getAll();
    KitchenResponseDTO update(Long id, KitchenRequestDTO requestDTO);
    void delete(Long id);

    // Kitchen creation by admin
    KitchenResponseDTO createKitchenWithAdmin(KitchenRequestDTO requestDTO, Long userId);
    KitchenResponseDTO joinKitchen(Long kitchenId, Long userId);
    KitchenResponseDTO joinKitchenByCode(String invitationCode, Long userId);
    KitchenResponseDTO getUserKitchen(Long userId);
}