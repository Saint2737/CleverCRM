@Service
public class AnalyticsAggregateService {
	
	private final AnalyticsAggregateRepository analyticsAggregateRepository;
	
	public AnalyticsAggregateService(AnalyticsAggregateRepository analyticsAggregateRepository) {
		this.analyticsAggregateRepository = analyticsAggregateRepository;
	}
	
	
	public AnalyticsSummary getSummary() {
		AnalyticsSummary summary = new AnalyticsSummary();
		summary.setTotalReservations(getTotalReservations());
		summary.setTotalCheckIns(getTotalCheckIns());
		summary.setTotalCheckOuts(getTotalCheckOuts());
		summary.setPositiveMessages(getPositiveMessages());
		summary.setNegativeMessages(getNegativeMessages());
		summary.setNeutralMessages(getNeutralMessages());
		summary.setTasksCompleted(getTasksCompleted());
		summary.setTasksPending(getTasksPending());
		summary.setRevenue(getRevenue());
		
		return summary;
	}

}
