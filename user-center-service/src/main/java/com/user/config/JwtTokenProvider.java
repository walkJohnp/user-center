package com.user.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenProvider {
    private String secret = "your-secret-key";
    private long validityInMilliseconds = 3600000; // 1小时

    public String createToken(String username, Collection<String> roles) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles", roles);

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + validityInMilliseconds))
            .signWith(SignatureAlgorithm.HS512, secret)
            .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = this.getUsername(token);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private UserDetails getUsername(String token) {
        // 根据用户名从数据库中获取用户信息，并返回用户名
        // 例如：
        // User user = userRepository.findByUsername(username);
        // return user.getUsername();
        return new User("121", "121", List.of());
    }

    public boolean validateToken(String token) {
        return true;
    }

    // 其他验证方法...
}

