

public class AnalyticsSummary {

	private int totalReservations;
	private int totalCheckIns;
	private int totalCheckOuts;
	private int positiveMessages;
	private int negativeMessages;
	private int neutralMessages;
	private int tasksCompleted;
	private int tasksPending;
	private double revenue;
	
	public int getTotalReservations() {
		return totalReservations;
	}
	public void setTotalReservations(int totalReservations) {
		this.totalReservations = totalReservations;
	}
	
	public int getTotalCheckIns() {
		return totalCheckIns;
	}
	public void setTotalCheckIns(int totalCheckIns) {
		this.totalCheckIns = totalCheckIns;
	}
	
	public int getTotalCheckOuts() {
		return totalCheckOuts;
	}
	public void setTotalCheckOuts(int totalCheckOuts) {
		this.totalCheckOuts =  totalCheckOuts;
	}
	
	public int getPositiveMessages() {
		return positiveMessages;
	}
	public void setPositiveMessages(int positiveMessages) {
		this.positiveMessages = positiveMessages;
	}
	
	public int getNegativeMessages() {
		return negativeMessages;
	}
	public void setNegativeMessages(int negativeMessages) {
		this.negativeMessages = negativeMessages;
	}
	
	public int getNeutralMessages() {
		return neutralMessages;
	}
	public void setNeutralMessages(int neutralMessages) {
		this.neutralMessages = neutralMessages;
	}
	
	public int getTasksCompleted() {
		return tasksCompleted;
	}
	public void setTasksCompleted(int tasksCompleted) {
		this.tasksCompleted = tasksCompleted;
	}
	
	public int getTasksPending() {
		return tasksPending;
	}
	public void setTasksPending(int tasksPending) {
		this.tasksPending = tasksPending;
	}
	
	public int getRevenue() {
		return revenue;
	}
	public void setRevenue(int revenue) {
		this.revenue = revenue;
	}
}
