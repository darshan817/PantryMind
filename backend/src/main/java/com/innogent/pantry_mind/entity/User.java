package com.innogent.pantry_mind.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
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
    
    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kitchen_id")
    private Kitchen kitchen;
    
    private String role;
}