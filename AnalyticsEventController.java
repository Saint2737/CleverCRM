
@RestController
@RequestMapping("/api/analyticsEvent")
public class AnalyticsEventController {
	
	private final AnalyticsEventService analyticsEventService;
	
	public AnalyticsEventController(AnalyticsEventService analyticsEventService) {
		this.analyticsEventService = analyticsEventService;
	}
	
	@GetMapping
	public PaginatedResponse<AnalyticsEvent> getAnalyticsEventByEventType(@RequestParam String eventType, Pageable pageable){
		return analyticsEventService.findByEventType(eventType, pageable);
	}
	
	@GetMapping
	public PaginatedReponse<AnalyticsEvent> getAnalyticsEventByModule(@RequestParam String module, Pageable pageable){
		return analyticsEventService.findByModule(module, pageable);
	}
	
	@GetMapping
	public PaginatedResponse<AnalyticsEvent> getAnalyticsEventByReferenceId(@PathVariable int referenceId, @RequestBody Pageable pageable){
		return analyticsEventService.findByReferenceId(referenceId, pageable);
	}
	
	@GetMapping
	public PaginatedResponse<AnalyticsEvent> getAnalyticsEventByReferenceType(@RequestParam String referenceType, Pageable pageable){
		return analyticsEventService.findByReferenceType(referenceType, pageable);
	}
}
