import java.time.LocalDateTime;

@Entity
@Table(name="settings")
public class Settings {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String key;
	//timezone,hotel_name,task_autoAssign
	private String value;
	//AfricaGMt, Safari Park, true
	private String description;
	private LocalDateTime updatedAt = LocalDateTime.now();
	
	public int getId() {
		return id;
	}
	public void setId() {
		this.id = id;
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedat(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	

}
