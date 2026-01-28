package DTO;

import java.time.LocalDate;

public record ReservationRequest() {
	
	String guestName;
	LocalDate checkInDate;
	LocalDate checkOutDate;
	String roomNumber;
	String source;
	String notes;

}
