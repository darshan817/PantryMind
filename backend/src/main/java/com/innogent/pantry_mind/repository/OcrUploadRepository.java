package com.innogent.pantry_mind.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.innogent.pantry_mind.entity.OcrUpload;

public interface OcrUploadRepository extends JpaRepository<OcrUpload, Long> {
}
