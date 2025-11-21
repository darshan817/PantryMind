package com.innogent.pantry_mind.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import java.util.Date;

@Data
public class CreateInventoryItemDTO {
    @NotBlank(message = "Name is required")
    private String name;
    
    private String description;
    private Long category_id;
    private Long unit_id;

    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be positive")
    private Long quantity;
    
    private String location;
    private Date expiryDate;
}