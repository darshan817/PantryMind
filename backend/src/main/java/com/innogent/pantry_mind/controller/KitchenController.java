package com.innogent.pantry_mind.controller;

import com.innogent.pantry_mind.dto.request.KitchenRequestDTO;
import com.innogent.pantry_mind.dto.response.KitchenResponseDTO;
import com.innogent.pantry_mind.service.KitchenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kitchens")
@RequiredArgsConstructor
@CrossOrigin
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
}
