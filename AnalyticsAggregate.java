import jakarta.persistence.GenerationType;

@Entity
@Table(name = "analyticsAggregate")
public class AnalyticsAggregate {
 
	@Id
	@GeneratedValue(Strategy = GenerationType.IDENTITY)
	private int id;
	
	private LocalDate date;
	private int totalReservations;
	private int totalCheckIns;
	private int totalCheckOuts;
	private int positiveMessages;
	private int negativeMessages;
	private int neutralMessages;
	private int taskCompleted;
	private int taskPending;
	private double revenue;
	
	@Column(columnDefinition = "TEXT")
	private String extraJson;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public int getTotalReservations() {
		return totalReservations;
		
	}
	public void setTotalReservations(int totalReservations) {
		this.totalReservations = totalReservations;
	}
	
	public int getTotalCheckouts() {
		return totalCheckOuts;
	}
	public void setTotalCheckOuts(int totalCheckOuts) {
		this.totalCheckOuts = totalCheckOuts;
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
	
	public int getTaskcompleted() {
		return taskCompleted;
	}
	public void setTaskcompleted(int taskCompleted) {
		this.taskCompleted = taskCompleted;
	}
	
	public int getTaskPending() {
		return taskPending;
	}
	public void setTaskPending(int taskPending) {
		this.taskPending = taskPending;
	}
	
	public double getRevenue() {
		return revenue;
	}
	public void setRevenue(double revenue) {
		this.revenue = revenue;
	}
	
	
}

