package com.innogent.pantry_mind.service.impl;

import com.innogent.pantry_mind.dto.request.LoginRequestDTO;
import com.innogent.pantry_mind.dto.request.RegisterRequestDTO;
import com.innogent.pantry_mind.dto.response.UserResponseDTO;
import com.innogent.pantry_mind.entity.User;
import com.innogent.pantry_mind.exception.ResourceNotFoundException;
import com.innogent.pantry_mind.mapper.UserMapper;
import com.innogent.pantry_mind.repository.UserRepository;
import com.innogent.pantry_mind.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDTO register(RegisterRequestDTO request){

        userRepository.findByEmail(request.getEmail()).ifPresent(u-> {
            throw new RuntimeException("Email already registered");
        });

        userRepository.findByEmail(request.getUsername()).ifPresent(u->{
            throw new RuntimeException("username already registered");
        });

        User user = userMapper.toUser(request);
        User saved = userRepository.save(user);

        return userMapper.toResponse(saved);
    }

    @Override
    public UserResponseDTO login(LoginRequestDTO req) {
        User user = userRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // NOTE: plain comparison for Phase-2. Hashing to be added in Phase-11.-----------------------
        if (user.getPasswordHash() == null || !user.getPasswordHash().equals(req.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return userMapper.toResponse(user);
    }

    @Override
    public UserResponseDTO getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return userMapper.toResponse(user);
    }

}