package com.project.lovlind.conmon.utils.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtProvider {
  private final JwtProperties jwtProperties;

  public String generateAccessToken(String subject, Long id, String authorities) {
    Date expiration = getAccessExpiration();
    return Jwts.builder()
        .subject(subject)
        .expiration(expiration)
        .claims(createClaims(id, authorities))
        .signWith(getEncodedKey())
        .compact();
  }

  public String generateRefreshToken(String subject) {
    return Jwts.builder()
        .subject(subject)
        .expiration(getRefreshExpiration())
        .signWith(getEncodedKey())
        .compact();
  }

  public Claims getClaims(String token) {
    return Jwts.parser()
        .verifyWith(getEncodedKey())
        .clockSkewSeconds(60)
        .build()
        .parseSignedClaims(token)
        .getPayload();
  }

  public Integer getRefreshTokenValidityInSeconds() {
    return jwtProperties.getRefreshTokenValidityInSeconds();
  }

  private Date getAccessExpiration() {
    return addExpirationData(jwtProperties.getAccessTokenValidityInSeconds()).getTime();
  }

  private Date getRefreshExpiration() {
    return addExpirationData(jwtProperties.getRefreshTokenValidityInSeconds()).getTime();
  }

  private Calendar addExpirationData(int expirationMinutes) {
    Calendar now = Calendar.getInstance();
    now.add(Calendar.MINUTE, expirationMinutes);
    return now;
  }

  private SecretKey getEncodedKey() {
    return Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8));
  }

  private Map<String, Object> createClaims(Long id, String authorities) {
    return Map.of("id", id, "authorities", authorities);
  }
}
