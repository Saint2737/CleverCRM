import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsAggregateController {

	private final AnalyticsAggregateService analyticsAggregateService;
	
	public AnalyticsAggregateController(AnalyticsAggregateService analyticsAggregateService) {
		this.analyticsAggregateService = analyticsAggregateService;
	}

	
	@GetMapping
	public ResponseEntity<AnalyticsSummary> getAnalysis(){
		AnalyticsSummary summary = analyticsAggregateService.getSummary();
		
		return ResponseEntity.ok(summary);
	}
	
	
	
}

