package com.innogent.pantry_mind.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PantryItem {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    
}
