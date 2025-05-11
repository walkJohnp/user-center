package com.user.controller;

import com.user.config.CustomAuthenticationManager;
import com.user.config.JwtTokenProvider;
import com.user.dto.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
    private CustomAuthenticationManager authenticationManager;

	@Autowired
    private JwtTokenProvider jwtTokenProvider;

	@PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.username(),
                    loginRequest.password()
                )
            );

            User user = (User) authentication.getPrincipal();
            String token = jwtTokenProvider.createToken(user.getUsername(), List.of("ROLE_USER"));

            return ResponseEntity.ok(Map.of(
                "username", user.getUsername(),
                "token", token
            ));
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("无效的用户名或密码");
        }
    }

}
