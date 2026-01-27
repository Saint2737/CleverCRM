package DTO;

public record GuestPreferencesRequest(
		UUID guestID,
		String bedType,
		String mealPreferences,
		String roomAllocation,
		String allergies,
		boolean needsAirportPickup
		) {}
