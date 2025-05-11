package com.user.service;

import com.user.entity.UserEntity;
import com.user.reposity.UserRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Resource
	private UserRepository userRepository;

	public UserEntity findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
}
