package entity;

import java.util.UUID;

@MappedSuperclass
public abstract class UuidEntity {
	
	@Id
	@GeneratedValue
	private UUID id;
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	
}
