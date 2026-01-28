package DTO;

import java.time.LocalDate;

public record ReservationResponse() {
	
	UUID id;
	String guestName;
	LocalDate checkInDate;
	LocalDate checkOutDate;
	String roomNumber;
	String status;
	String source;
	String notes;
	
	

}
