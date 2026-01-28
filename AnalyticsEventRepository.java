

public interface AnalyticsEventRepository extends JpaRepository<AnalyticsEvent, Integer> {

	Page<AnalyticsEvent> findByEventType(String eventType, Pageable pageable);
	Page<AnalyticsEvent> findByModule(String module, Pageable pageable);
	Page<AnalyticsEvent> findByReferenceId(int referenceId, Pageable pageable);
	Page<AnalyticsEvent> findByReferenceType(String referenceType, Pageable pageable);
	
}
