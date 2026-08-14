package com.cleverCRM.controller;

import java.util.Set;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cleverCRM.service.DocumentIngestionService;

@RestController
@RequestMapping("/api/ingest")
public class FileIngestionController {

    private final DocumentIngestionService ingestionService;

    public FileIngestionController(DocumentIngestionService ingestionService) {
        this.ingestionService = ingestionService;
    }

    private static final long MAX_FILE_SIZE_BYTES = 10L * 1024 * 1024;

    private static final Set<String> ALLOWED_CONTENT_TYPES = Set.of(
            "text/plain",
            "text/csv",
            "application/pdf");

    @PostMapping("/file")
    public String uploadFile(
            @RequestParam String sourceType,
            @RequestParam String sourceId,
            @RequestParam MultipartFile file
    ) {

        if (sourceType == null || sourceType.isBlank() || sourceId == null || sourceId.isBlank()) {
            throw new IllegalArgumentException("sourceType and sourceId must not be blank");
        }
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("file must not be empty");
        }
        if (file.getSize() > MAX_FILE_SIZE_BYTES) {
            throw new IllegalArgumentException("file must not exceed 10MB");
        }
        if (!ALLOWED_CONTENT_TYPES.contains(file.getContentType())) {
            throw new IllegalArgumentException("unsupported content type: " + file.getContentType());
        }

        ingestionService.ingestFile(null, sourceType, sourceId, file);

        return "File ingested successfully";
    }
}