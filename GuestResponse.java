package DTO;

import java.util.UUID;

public record GuestResponse(
		UUID id,
		String fullName,
		String phone,
		String email,
		String nationality
		) {}
