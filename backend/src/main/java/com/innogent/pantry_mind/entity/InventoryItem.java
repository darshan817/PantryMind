package com.innogent.pantry_mind.entity;

import jakarta.persistence.*;
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
    
    @Column(name = "kitchen_id")
    private Long kitchenId; // Changed to camelCase
    
    @Column(name = "created_by") 
    private Long createdBy; // Changed to camelCase

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id")
    private Unit unit;
    
    private Long quantity;
    private String location;
    private Date expiryDate;

    @CreationTimestamp
    private Date createdAt; // Changed to camelCase
}
