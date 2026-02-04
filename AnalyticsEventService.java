
@Service
public class AnalyticsEventService {

	private final AnalyticsEventRepository analyticsEventRepository;
	
	public AnalyticsEventService(AnalyticsEventRepository analyticsEventRepository) {
		this.analyticsEventRepository = analyticsEventRepository;
	}
	
	@Transactional(readOnly = true)
	public PaginatedResponse<AnalyticsEvent> getByEventType(String eventType, Pageable pageable){
		Page<AnalyticsEvent> page =  analyticsEventRepository.findByEventType(eventType, pageable)
				.map(this::toDTO);
		
		return toPaginatedResponse(page);
	}
	
	@Transactional(readOnly = true)
	public PaginatedResponse<AnalyticsEvent> getByModule(String module, Pageable pageable){
		Page<AnalyticsEvent> page = analyticsEventRepository.findByModule(module, pageable)
				.map(this::toDTO);
		
		return toPaginatedResponse(page);
	}
	
	@Transactional(readOnly = true)
	public PaginatedResponse<AnalyticsEvent> getByReferenceType(String referenceType, Pageable pageable){
		Page<AnalyticsEvent> page = analyticsEventRepository.findByReferenceType(referenceType, pageable)
				.map(this::toDTO);
		
		return toPaginatedResponse(page);
	}
	
	@Transactional(readOnly = true)
	public PaginatedResponse<AnalyticsEvent> getByReferenceId(int referenceId, Pageable pageable){
		Page<AnalyticsEvent> page = analyticsEventRepository.findByReferenceId(referenceId, pageable)
				.map(this::toDTO);
		
		return toPaginatedResponse(page);
	}
	
	private AnalyticsEventDTO toDTO(AnalyticsEvent entity) {
		return new AnalyticsEventDTO(
				entity.getEventType(),
				entity.getModule(),
				entity.getReferenceType(),
				entity.getReferenceId());
		
	}
	
	private <T> PaginatedResponse<T> toPaginatedResponse(Page<T> page){
		return new PaginatedResponse<>(
				page.getContent(),
				page.getPage(),
				page.getSize(),
				page.getTotalElements(),
				page.getTotalPages(),
				page.getHasNext(),
				page.getHasPrevious()
				);
				
	}
	
	
	
}
