import java.time.LocalDateTime;

@Entity
@Table(name = "analyticsEvent")
public class AnalyticsEvent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String eventType; //message_received,task_completed,
	private String module; //reservations,tasks, guests
	private int referenceId;
	private String referenceType;
	private String metadataJson;
	private LocalDateTime timestamp = LocalDateTime.now();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getEventType() {
		return eventType;
	}
	public void setEventType() {
		this.eventType = eventType;
	}
	
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public int getReferenceId() {
		return referenceId;
	}
	public void setReferenceId(int referenceId) {
		this.referenceId = referenceId;
	}
	public String getReferenceType() {
		return referenceType;
	}
	public void setReferenceType() {
		this.referenceType = referenceType;
	}
	public String getMetadataJson() {
		return metadataJson;
	}
	public void setMetadataJson(String metadatJson) {
		this.metadataJson = metadataJson;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timeStamp) {
		this.timestamp = timestamp;
	}
	
	
}
