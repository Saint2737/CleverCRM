package com.cleverCRM.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cleverCRM.dto.AnalyticsSummary;
import com.cleverCRM.service.AnalyticsAggregateService;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsAggregateController {

    private final AnalyticsAggregateService analyticsService;

    public AnalyticsAggregateController(
            AnalyticsAggregateService analyticsService) {
        this.analyticsService = analyticsService;
    }

    /* ================= DASHBOARD SUMMARY ================= */

    @GetMapping("/summary")
    public ResponseEntity<AnalyticsSummary> getSummary(

            @RequestParam(defaultValue = "week")
            String filter,

            @RequestParam(required = false)
            String start,

            @RequestParam(required = false)
            String end) {

        return ResponseEntity.ok(
                analyticsService.getSummary(
                        filter,
                        start,
                        end
                )
        );
    }

    /* ================= AI INSIGHTS ================= */

    @GetMapping("/insights")
    public ResponseEntity<List<String>> getInsights() {

        return ResponseEntity.ok(
                analyticsService.generateInsights()
        );
    }
}