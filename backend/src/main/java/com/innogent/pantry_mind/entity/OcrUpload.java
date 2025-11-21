package com.innogent.pantry_mind.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "ocr_uploads")
@NoArgsConstructor
@AllArgsConstructor
public class OcrUpload {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "kitchen_id", nullable = false)
    private Long kitchenId;

    @Column(name = "uploaded_by", nullable = false)
    private Long uploadedBy;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "raw_ocr_text", columnDefinition = "TEXT")
    private String rawOcrText;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    public OcrUpload(Long kitchenId, Long uploadedBy, String imageUrl) {
        this.kitchenId = kitchenId;
        this.uploadedBy = uploadedBy;
        this.imageUrl = imageUrl;
    }
}
