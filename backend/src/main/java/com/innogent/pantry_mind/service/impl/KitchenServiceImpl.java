package com.innogent.pantry_mind.service.impl;

import com.innogent.pantry_mind.dto.request.KitchenRequestDTO;
import com.innogent.pantry_mind.dto.response.KitchenResponseDTO;
import com.innogent.pantry_mind.entity.Kitchen;
import com.innogent.pantry_mind.entity.Role;
import com.innogent.pantry_mind.entity.User;
import com.innogent.pantry_mind.exception.ResourceNotFoundException;
import com.innogent.pantry_mind.mapper.KitchenMapper;
import com.innogent.pantry_mind.repository.KitchenRepository;
import com.innogent.pantry_mind.repository.RoleRepository;
import com.innogent.pantry_mind.repository.UserRepository;
import com.innogent.pantry_mind.service.KitchenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KitchenServiceImpl implements KitchenService {
    
    private final KitchenRepository kitchenRepository;
    private final KitchenMapper kitchenMapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public KitchenResponseDTO create(KitchenRequestDTO requestDTO) {
        Kitchen kitchen = kitchenMapper.toEntity(requestDTO);
        Kitchen saved = kitchenRepository.save(kitchen);
        return kitchenMapper.toResponse(saved);
    }

    @Override
    public KitchenResponseDTO getById(Long id) {
        Kitchen kitchen = kitchenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Kitchen not found with id: " + id));
        return kitchenMapper.toResponse(kitchen);
    }

    @Override
    public List<KitchenResponseDTO> getAll() {
        return kitchenRepository.findAll().stream()
                .map(kitchenMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public KitchenResponseDTO update(Long id, KitchenRequestDTO requestDTO) {
        Kitchen kitchen = kitchenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Kitchen not found with id: " + id));
        kitchen.setName(requestDTO.getName());
        Kitchen updated = kitchenRepository.save(kitchen);
        return kitchenMapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        if (!kitchenRepository.existsById(id)) {
            throw new ResourceNotFoundException("Kitchen not found with id: " + id);
        }
        kitchenRepository.deleteById(id);
    }

    // Create kitchen and assign user as ADMIN
    @Override
    @Transactional
    public KitchenResponseDTO createKitchenWithAdmin(KitchenRequestDTO requestDTO, Long userId) {
        // Create kitchen
        Kitchen kitchen = kitchenMapper.toEntity(requestDTO);
        Kitchen savedKitchen = kitchenRepository.save(kitchen);

        // Assign user to kitchen and make ADMIN
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Role adminRole = roleRepository.findByName("ADMIN")
                .orElseGet(() -> roleRepository.save(Role.builder().name("ADMIN").build()));

        user.setKitchen(savedKitchen);
        user.setRole(adminRole);
        userRepository.save(user);

        return kitchenMapper.toResponse(savedKitchen);
    }

    // Join kitchen and assign user as MEMBER
    @Override
    @Transactional
    public KitchenResponseDTO joinKitchen(Long kitchenId, Long userId) {
        Kitchen kitchen = kitchenRepository.findById(kitchenId)
                .orElseThrow(() -> new ResourceNotFoundException("Kitchen not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Role memberRole = roleRepository.findByName("MEMBER")
                .orElseGet(() -> roleRepository.save(Role.builder().name("MEMBER").build()));

        user.setKitchen(kitchen);
        user.setRole(memberRole);
        userRepository.save(user);

        return kitchenMapper.toResponse(kitchen);
    }

    // Join kitchen by invitation code
    @Override
    @Transactional
    public KitchenResponseDTO joinKitchenByCode(String invitationCode, Long userId) {
        Kitchen kitchen = kitchenRepository.findByInvitationCode(invitationCode)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid invitation code"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Role memberRole = roleRepository.findByName("MEMBER")
                .orElseGet(() -> roleRepository.save(Role.builder().name("MEMBER").build()));

        user.setKitchen(kitchen);
        user.setRole(memberRole);
        userRepository.save(user);

        return kitchenMapper.toResponse(kitchen);
    }

    // Get user's kitchen
    @Override
    public KitchenResponseDTO getUserKitchen(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        if (user.getKitchen() == null) {
            throw new ResourceNotFoundException("User is not part of any kitchen");
        }
        
        return kitchenMapper.toResponse(user.getKitchen());
    }
}