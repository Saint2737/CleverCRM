package entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name ="guest_history")
public class GuestHistory extends UuidEntity {
	
	@ManyToOne
	@JoinColumn(name = "guest_id")
	private Guest guest;
	
	private LocalDateTime checkInDate;
	private LocalDateTime checkOutDate;
	private String roomType;
	private BigDecimal totalBill;
	
	@Column(ColumnDefinition = "TEXT")
	private String feedbackSummary;
	
	public Guest getGuest() {
		return guest;
	}
	public void setGuest(Guest guest) {
		this.guest = guest;
	}
	
	public LocalDateTime getCheckInDate() {
		return checkInDate;
	}
	public void setCheckInDate(LocalDateTime checkInDate) {
		this.checkInDate = checkInDate;
	}
	
	public LocalDateTime getCheckOutDate() {
		return checkOutDate;
	}
	public void setCheckOutDate(LocalDateTime checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	
	public BigDecimal getTotalBill() {
		return totalBill;
	}
	public void setTotalBill(BigDecimal totalBill) {
		this.totalBill = totalBill;
	}
	
	public String getFeedbackSummary() {
		return feedbackSummary;
	}
	public void setFeedbackSummary(String feedbackSummary) {
		this.feedbackSummary = feedbackSummary;
	}

}
