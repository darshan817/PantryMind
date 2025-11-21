package com.innogent.pantry_mind.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "ai_extracted_items")
public class AiExtractedItems {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "ocr_upload_id")
    private Long ocrUploadId;

    @Column(name = "item_name")
    private Long itemName;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "predicted_expiry_date")
    private LocalDate predictedExpiryDate;

    private BigDecimal confidence;

    @Column(name = "raw_ai_json", columnDefinition = "jsonb")
    private String rawAiJson;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}
