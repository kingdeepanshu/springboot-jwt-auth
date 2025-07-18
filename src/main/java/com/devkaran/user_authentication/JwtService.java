package com.devkaran.user_authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.List;

@Service
public class JwtService {
    private final String sec = "qwertyuiopasdfghjklzxcvbnmqwertyuiop";
    private final long exp = 1000 * 60 * 60 * 24;

    private final Key key = Keys.hmacShaKeyFor(sec.getBytes());

    public String generateToken(String username, List<String> roles){
        return Jwts.builder()
                .setSubject(username)
                .setIssuer("MyApp")
                .signWith(key)
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+exp))
                .compact();
    }

    private Claims getClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token){
        return getClaims(token).getSubject();
    }

    public List<String> getRoles(String token){
        return (List<String>)getClaims(token).get("roles");
    }

}
