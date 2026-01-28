
public interface AnalyticsAggregateRepository extends JpaRepository<AnalyticsAggregate, Integer>{

	int getTotalReservations(int totalReservations);
	int getTotalCheckIns(int totalCheckIns);
	int getTotalCheckOuts(int totalCheckOuts);
	int getPositiveMessages(int positiveMessages);
	int getNegativeMessages(int negativeMessages);
	int getNeutralMessages(int neutralMessages);
	int getTaskCompleted(int taskCompleted);
	int getTaskPending(int taskPending);
	double getRevenue(double revenue);
}
