package com.innogent.pantry_mind.dto;

import lombok.Data;
import java.util.Date;

@Data
public class InventoryItemDTO {
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
    private Date created_at;
}
