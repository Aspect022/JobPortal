package org.example.jobportal.Security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "i0t7zv3QO4Yk7lLqWxSdQ5w8PmF1u+zKx3M1oTqD4uM=";// Replace with environment variable
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10 hours

    // Generate JWT Token
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuer("JobPortal")  // Optional: Set Issuer
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Extract username from token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Validate Token
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    // Check if Token is Expired
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Extract Expiration Date
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Extract Specific Claims
    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    // Extract All Claims with Exception Handling
    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("Token Expired", e);
        } catch (MalformedJwtException e) {
            throw new RuntimeException("Invalid Token", e);
        } catch (Exception e) {
            throw new RuntimeException("Error Parsing Token", e);
        }
    }

    // Get Secret Key for Signing
    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // (Optional) Blacklist Token - You need to store and check blacklisted tokens in DB/Redis
    public boolean isTokenBlacklisted(String token) {
        // Implement logic to check if token is blacklisted
        return false;
    }
}
