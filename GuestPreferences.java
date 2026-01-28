package entity;

import java.util.UUID;

@Entity
@Table(name = "guest_preferences")
public class GuestPreferences {
	
	@Id
	@GeneratedValue
	private UUID id;
	
	@OneToOne
	@JoinColumn(name =="guest_id")
	private Guest guest;
	
	private Boolean likesQuietRooms;
	private Boolean prefersSeaView;
	private Boolean lateCheckOut;
	private String bedTypePreference;
	private String allergies;
	private String mealPreferences;
	
	@Column(columnDefinition = "TEXT")
	private String customPreferences;
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	
	public Guest getGuest() {
		return guest;
	}
	public void setGuest(Guest guest) {
		this.guest = guest;
	}
	
	public Boolean getLikesQuietRooms() {
		return likesQuietRooms;
	}
	public void setLikesQuietRooms(Boolean likesQuietRooms) {
		this.likesQuietRooms = likesQuietRooms;
	}
	
	public Boolean getPrefersSeaView() {
		return prefersSeaView;
	}
	public void setPrefersSeaView(Boolean prefersSeaView) {
		this.prefersSeaView = prefersSeaView;
	}
	
	public Boolean getLateCheckout() {
		return lateCheckOut;
	}
	public void setLateCheckOut(Boolean lateCheckOut) {
		this.lateCheckOut = lateCheckOut;
	}
	
	public String getBedTypePreferences() {
		return bedTypePreference;
	}
	public void setBedTypePreferences(String bedTypePreference) {
		this.bedTypePreference = bedTypePreference;
	}
	
	public String getAllergies() {
		return allergies;
	}
	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}
	
	public String getMealPreferences() {
		return mealPreferences;
	}
	public void setMealPreferences(String mealPreferences) {
		this.mealPreferences = mealPreferences;
	}
	
	public String getCustomPreferences() {
		return customPreferences;
	}
	public void setCustomPreferences(String  customPreferences) {
		this.customPreferences = customPreferences;
	}
	
	

}
