package com.cleverCRM.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cleverCRM.dto.VectorSearchResult;
import com.cleverCRM.service.EmbeddingService;

@RestController
@RequestMapping("/api/embedding")
public class EmbeddingController {

    private static final int MAX_TEXT_LENGTH = 8000;
    private static final int MAX_TOP_K = 50;

    private final EmbeddingService embeddingService;

    public EmbeddingController(EmbeddingService embeddingService) {
        this.embeddingService = embeddingService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createEmbedding(
            @RequestParam String text) {

        return ResponseEntity.ok(
                embeddingService.createEmbeddingAndStore(validateText(text)));
    }

    @PostMapping("/checkSimilar")
    public ResponseEntity<List<VectorSearchResult>> getSimilarVector(
            @RequestParam String text,
            @RequestParam(defaultValue = "5") int topK) {

        if (topK < 1 || topK > MAX_TOP_K) {
            throw new IllegalArgumentException("topK must be between 1 and " + MAX_TOP_K);
        }

        return ResponseEntity.ok(
                embeddingService.querySimilar(validateText(text), topK));
    }

    private static String validateText(String text) {
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException("text must not be blank");
        }
        if (text.length() > MAX_TEXT_LENGTH) {
            throw new IllegalArgumentException("text must not exceed " + MAX_TEXT_LENGTH + " characters");
        }
        return text;
    }
}