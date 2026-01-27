package DTO;

public record GuestHistoryRequest(
		UUID id,
		String roomUsed,
		String purpose,
		String notes
		) {

}
