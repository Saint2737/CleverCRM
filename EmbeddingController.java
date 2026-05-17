package com.cleverCRM.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cleverCRM.dto.VectorSearchResult;
import com.cleverCRM.service.EmbeddingService;

@RestController
@RequestMapping("/api/embedding")
@CrossOrigin(origins = "*")
public class EmbeddingController {

    private final EmbeddingService embeddingService;

    public EmbeddingController(EmbeddingService embeddingService) {
        this.embeddingService = embeddingService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createEmbedding(
            @RequestParam String text) {

        return ResponseEntity.ok(
                embeddingService.createEmbeddingAndStore(text));
    }

    @PostMapping("/checkSimilar")
    public ResponseEntity<List<VectorSearchResult>> getSimilarVector(
            @RequestParam String text,
            @RequestParam(defaultValue = "5") int topK) {

        return ResponseEntity.ok(
                embeddingService.querySimilar(text, topK));
    }
}