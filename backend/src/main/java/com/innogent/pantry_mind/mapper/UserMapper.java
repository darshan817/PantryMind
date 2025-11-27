package com.innogent.pantry_mind.mapper;

import com.innogent.pantry_mind.dto.request.RegisterRequestDTO;
import com.innogent.pantry_mind.dto.response.UserResponseDTO;
import com.innogent.pantry_mind.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {KitchenMapper.class})
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "kitchen", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "googleId", ignore = true)
    @Mapping(target = "isActive", constant = "true")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "passwordHash", source = "password")
    User toUser(RegisterRequestDTO request);

    @Mapping(target = "role", source = "role.name")
    @Mapping(target = "kitchen", source = "kitchen")
    UserResponseDTO toResponse(User user);
}