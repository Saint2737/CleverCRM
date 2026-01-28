package entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "guests")
public class Guest {
	
	@Id 
	@GeneratedValue
	private UUID id;
	
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String gender;
	
	private int totalVisits;
	private BigDecimal averageSpending;
	
	private String loyaltyTier;   // e.g. Gold, silver, Bronze
	private String notes;
	
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	@OneToMany(mappedBy = "guest", cascade = CascadeType.ALL)
	private List<GuestHistory> history = new ArrayList<>();
	
	@OneToOne(mappedBy = "guest", cascade = CascadeType.ALL)
	private GuestPreferences preferences;
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id =id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getGender() {
		return gender;
			
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public int getTotalVisits() {
		return totalVisits;
	}
	public void setTotalVisits(int totalVisits) {
		this.totalVisits = totalVisits;
	}
	
	public BigDecimal getAverageSpending() {
		return averageSpending;
	}
	public void setAverageSpending(BigDecimal averageSpending) {
		this.averageSpending = averageSpending;
	}
	
	public String getLoyaltyTier() {
		return loyaltyTier;
	}
	public void setLoyaltyTier(String loyaltyTier) {
		this.loyaltyTier = loyaltyTier;
	}
	
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public List<GuestHistory> getHistory(){
		return history;
	}
	public void setHistory(List<GuestHistory> history) {
		this.history = history;
	}
	
	public GuestPreferences getPreferences() {
		return preferences;
	}
	public void setPreferences(GuestPreferences preferences) {
		this.preferences = preferences;
	}
	public void setNationality(String nationality) {
		// TODO Auto-generated method stub
		
	}
	public String getNationality() {
		// TODO Auto-generated method stub
		return null;
	}
	public Guest orElseThrow(Object object) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	

}
