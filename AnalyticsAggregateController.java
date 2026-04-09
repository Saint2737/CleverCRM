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

	private final AnalyticsAggregateService analyticsAggregateService;
	
	public AnalyticsAggregateController(AnalyticsAggregateService analyticsAggregateService) {
		this.analyticsAggregateService = analyticsAggregateService;
	}

	
	@GetMapping
	public ResponseEntity<AnalyticsSummary> getAnalysis(
	    @RequestParam(defaultValue = "week") String filter,
	    @RequestParam(required = false) String start,
	    @RequestParam(required = false) String end
	) {
	    return ResponseEntity.ok(
	        analyticsAggregateService.getSummary(filter, start, end)
	    );
	}
	
	@GetMapping("/insights")
	public List<String> getInsights() {
	    return analyticsAggregateService.generateInsights();
	}
	
	
}

