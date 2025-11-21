package com.innogent.pantry_mind.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data
@Entity
public class InventoryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Long kitchen_id; //FK
    private Long category_id; //FK
    private Long unit_id;   //FK
    private Long created_by; //FK
    private Long quantity;
    private String location;
    private Date expiryDate;

    @CreationTimestamp
    private Date created_at;
}