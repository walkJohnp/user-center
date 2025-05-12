package com.user.dto.request;

public record RegistrationRequest(
		String username,
		String email,
		String password
) {
}
