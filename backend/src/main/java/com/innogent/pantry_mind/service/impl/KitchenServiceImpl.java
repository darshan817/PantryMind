package com.innogent.pantry_mind.service.impl;

import com.innogent.pantry_mind.dto.request.KitchenRequestDTO;
import com.innogent.pantry_mind.dto.response.KitchenResponseDTO;
import com.innogent.pantry_mind.entity.Kitchen;
import com.innogent.pantry_mind.exception.ResourceNotFoundException;
import com.innogent.pantry_mind.mapper.KitchenMapper;
import com.innogent.pantry_mind.repository.KitchenRepository;
import com.innogent.pantry_mind.service.KitchenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KitchenServiceImpl implements KitchenService {
    
    private final KitchenRepository kitchenRepository;
    private final KitchenMapper kitchenMapper;

    @Override
    public KitchenResponseDTO create(KitchenRequestDTO requestDTO) {
        Kitchen kitchen = kitchenMapper.toEntity(requestDTO);
        Kitchen saved = kitchenRepository.save(kitchen);
<<<<<<< HEAD
        return kitchenMapper.toResponse(saved);
=======
        return kitchenMapper.toResponseDTO(saved);
>>>>>>> 6bd847bd126260b6bd160a5f6fc8318ae04d4487
    }

    @Override
    public KitchenResponseDTO getById(Long id) {
        Kitchen kitchen = kitchenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Kitchen not found with id: " + id));
<<<<<<< HEAD
        return kitchenMapper.toResponse(kitchen);
=======
        return kitchenMapper.toResponseDTO(kitchen);
>>>>>>> 6bd847bd126260b6bd160a5f6fc8318ae04d4487
    }

    @Override
    public List<KitchenResponseDTO> getAll() {
        return kitchenRepository.findAll().stream()
<<<<<<< HEAD
                .map(kitchenMapper::toResponse)
=======
                .map(kitchenMapper::toResponseDTO)
>>>>>>> 6bd847bd126260b6bd160a5f6fc8318ae04d4487
                .collect(Collectors.toList());
    }

    @Override
    public KitchenResponseDTO update(Long id, KitchenRequestDTO requestDTO) {
        Kitchen kitchen = kitchenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Kitchen not found with id: " + id));
        kitchen.setName(requestDTO.getName());
        Kitchen updated = kitchenRepository.save(kitchen);
<<<<<<< HEAD
        return kitchenMapper.toResponse(updated);
=======
        return kitchenMapper.toResponseDTO(updated);
>>>>>>> 6bd847bd126260b6bd160a5f6fc8318ae04d4487
    }

    @Override
    public void delete(Long id) {
        if (!kitchenRepository.existsById(id)) {
            throw new ResourceNotFoundException("Kitchen not found with id: " + id);
        }
        kitchenRepository.deleteById(id);
    }
}
