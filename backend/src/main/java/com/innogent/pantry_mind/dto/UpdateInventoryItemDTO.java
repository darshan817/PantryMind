package com.innogent.pantry_mind.dto;

import jakarta.validation.constraints.Positive;
import lombok.Data;
import java.util.Date;

@Data
public class UpdateInventoryItemDTO {
    private String name;
    private String description;
    private Long category_id;
    private Long unit_id;

    @Positive(message = "Quantity must be positive")
    private Long quantity;
    
    private String location;

    private Date expiryDate;
}