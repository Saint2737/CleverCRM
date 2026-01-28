import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.GenerationType;

@Entity
@Table(name = "notification")
public class Notification {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int staffId;
	private String title;
	private String message;
	private String channel;
	private String category;
	private LocalDateTime createdAt = LocalDateTime.now();
	private LocalDateTime readAt;
	
	@Enumerated(Enum.STRING)
	private Status status = Status.UNREAD;
	
	public enum Status{
		UNREAD,
		READ
	}
	
	public int getId() {
		return id;
		
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getStaffId() {
		return staffId;
		
	}
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getChannel() {
		return channel;
	}
	public void setChannel() {
		this.channel = channel;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;	
	}
	
	public LocalDateTime getReadAt() {
		return readAt;
	}
	public void setReadAt(LocalDateTime readAt) {
		this.readAt = readAt;
	}
	
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
   	
	
	
	

}
