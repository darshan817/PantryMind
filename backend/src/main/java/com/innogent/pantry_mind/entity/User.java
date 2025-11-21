package com.innogent.pantry_mind.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String name;
    private String email;
    private String passwordHash;
    private String googleId;
    private Boolean isActive = true;
    private LocalDateTime createdAt = LocalDateTime.now();

    private Long kitchenId;
    private String role;
}