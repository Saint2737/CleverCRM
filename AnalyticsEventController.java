package com.cleverCRM.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import com.cleverCRM.dto.AnalyticsEventDTO;
import com.cleverCRM.dto.PaginatedResponse;
import com.cleverCRM.service.AnalyticsEventService;

@RestController
@RequestMapping("/api/analytics-events")
public class AnalyticsEventController {

    private final AnalyticsEventService analyticsEventService;

    public AnalyticsEventController(AnalyticsEventService analyticsEventService) {
        this.analyticsEventService = analyticsEventService;
    }

    // ✅ Filter by eventType
    @GetMapping("/by-eventType")
    public PaginatedResponse<AnalyticsEventDTO> getByEventType(
            @RequestParam String eventType,
            Pageable pageable) {

        return analyticsEventService.getByEventType(eventType, pageable);
    }

    // ✅ Filter by module
    @GetMapping("/by-module")
    public PaginatedResponse<AnalyticsEventDTO> getByModule(
            @RequestParam String module,
            Pageable pageable) {

        return analyticsEventService.getByModule(module, pageable);
    }

    // ✅ Filter by referenceId
    @GetMapping("/by-reference-id/{referenceId}")
    public PaginatedResponse<AnalyticsEventDTO> getByReferenceId(
            @PathVariable int referenceId,
            Pageable pageable) {

        return analyticsEventService.getByReferenceId(referenceId, pageable);
    }

    // ✅ Filter by referenceType
    @GetMapping("/by-reference-type")
    public PaginatedResponse<AnalyticsEventDTO> getByReferenceType(
            @RequestParam String referenceType,
            Pageable pageable) {

        return analyticsEventService.getByReferenceType(referenceType, pageable);
    }
}