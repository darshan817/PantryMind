package com.innogent.pantry_mind.controller;

import com.innogent.pantry_mind.dto.request.KitchenRequestDTO;
import com.innogent.pantry_mind.dto.response.KitchenResponseDTO;
import com.innogent.pantry_mind.service.KitchenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/kitchens")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class KitchenController {
    
    private final KitchenService kitchenService;

    @PostMapping
    public ResponseEntity<KitchenResponseDTO> create(@RequestBody KitchenRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(kitchenService.create(requestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<KitchenResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(kitchenService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<KitchenResponseDTO>> getAll() {
        return ResponseEntity.ok(kitchenService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<KitchenResponseDTO> update(@PathVariable Long id, @RequestBody KitchenRequestDTO requestDTO) {
        return ResponseEntity.ok(kitchenService.update(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        kitchenService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Create kitchen with admin assignment
    @PostMapping("/create-with-admin")
    public ResponseEntity<KitchenResponseDTO> createWithAdmin(
            @RequestBody KitchenRequestDTO requestDTO,
            @RequestParam Long userId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(kitchenService.createKitchenWithAdmin(requestDTO, userId));
    }

    // Join kitchen endpoint
    @PostMapping("/{kitchenId}/join")
    public ResponseEntity<KitchenResponseDTO> joinKitchen(
            @PathVariable Long kitchenId,
            @RequestParam Long userId) {
        return ResponseEntity.ok(kitchenService.joinKitchen(kitchenId, userId));
    }

    // Join kitchen by invitation code
    @PostMapping("/join-by-code")
    public ResponseEntity<KitchenResponseDTO> joinByInvitationCode(
            @RequestBody Map<String, Object> request) {
        String invitationCode = (String) request.get("invitationCode");
        Long userId = Long.valueOf(request.get("userId").toString());
        return ResponseEntity.ok(kitchenService.joinKitchenByCode(invitationCode, userId));
    }

    // Get user's kitchen
    @GetMapping("/user/{userId}")
    public ResponseEntity<KitchenResponseDTO> getUserKitchen(@PathVariable Long userId) {
        return ResponseEntity.ok(kitchenService.getUserKitchen(userId));
    }
}