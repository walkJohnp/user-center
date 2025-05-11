package com.user.dto;

import lombok.Data;

import java.util.List;

@Data
public class CustomOAuth2User {
	private String username;

	private String password;

	private List<String> roles;
}
