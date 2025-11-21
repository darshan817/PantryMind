package com.innogent.pantry_mind.service;

import com.innogent.pantry_mind.dto.request.LoginRequestDTO;
import com.innogent.pantry_mind.dto.request.RegisterRequestDTO;
import com.innogent.pantry_mind.dto.response.UserResponseDTO;

public interface UserService {
    UserResponseDTO register(RegisterRequestDTO request);
    UserResponseDTO login(LoginRequestDTO request);
    UserResponseDTO getUserById(Long userId);



}





