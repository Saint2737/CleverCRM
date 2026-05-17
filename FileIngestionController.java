package com.cleverCRM.controller;

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

    @PostMapping("/file")
    public String uploadFile(
            @RequestParam String sourceType,
            @RequestParam String sourceId,
            @RequestParam MultipartFile file
    ) {

        ingestionService.ingestFile(null, sourceType, sourceId, file);

        return "File ingested successfully";
    }
}