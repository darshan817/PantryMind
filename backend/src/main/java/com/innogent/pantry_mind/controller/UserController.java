package com.innogent.pantry_mind.controller;


import com.innogent.pantry_mind.dto.request.LoginRequestDTO;
import com.innogent.pantry_mind.dto.request.RegisterRequestDTO;
import com.innogent.pantry_mind.dto.request.UpdateUserRequestDTO;

import java.util.List;
import com.innogent.pantry_mind.dto.response.UserResponseDTO;
import com.innogent.pantry_mind.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@CrossOrigin

public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody RegisterRequestDTO request){
        UserResponseDTO resp = userService.register(request);
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody LoginRequestDTO request) {
        UserResponseDTO resp = userService.login(request);
        return ResponseEntity.ok(resp);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getById(@PathVariable Long id) {
        UserResponseDTO resp = userService.getUserById(id);
        return ResponseEntity.ok(resp);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @RequestBody UpdateUserRequestDTO request) {
        UserResponseDTO resp = userService.updateUser(id, request);
        return ResponseEntity.ok(resp);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}