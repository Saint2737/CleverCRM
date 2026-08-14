
@Service
public class AnalyticsEventService {

	private final AnalyticsEventRepository analyticsEventRepository;
	
	public AnalyticsEventService(AnalyticsEventRepository analyticsEventRepository) {
		this.analyticsEventRepository = analyticsEventRepository;
	}
	
	@Transactional(readOnly = true)
	public PaginatedResponse<AnalyticsEventDTO> getByEventType(String eventType, Pageable pageable){
		return toPage(analyticsEventRepository.findByEventType(eventType, pageable));
	}
	
	@Transactional(readOnly = true)
	public PaginatedResponse<AnalyticsEventDTO> getByModule(String module, Pageable pageable){
		return toPage(analyticsEventRepository.findByModule(module, pageable));
	}
	
	@Transactional(readOnly = true)
	public PaginatedResponse<AnalyticsEventDTO> getByReferenceType(String referenceType, Pageable pageable){
		return toPage(analyticsEventRepository.findByReferenceType(referenceType, pageable));
	}
	
	@Transactional(readOnly = true)
	public PaginatedResponse<AnalyticsEventDTO> getByReferenceId(int referenceId, Pageable pageable){
		return toPage(analyticsEventRepository.findByReferenceId(referenceId, pageable));
	}
	
	private PaginatedResponse<AnalyticsEventDTO> toPage(Page<AnalyticsEvent> events) {
		return PageMapper.toPaginatedResponse(events.map(this::toDTO));
	}
	
	private AnalyticsEventDTO toDTO(AnalyticsEvent entity) {
		return new AnalyticsEventDTO(
				entity.getEventType(),
				entity.getModule(),
				entity.getReferenceType(),
				entity.getReferenceId());
		
	}
	
}
