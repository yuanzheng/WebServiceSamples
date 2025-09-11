package com.ytechtrade.inventorymanagementsystem.security;

import com.ytechtrade.inventorymanagementsystem.security.config.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

@Service
@Slf4j
@RequiredArgsConstructor
public class JwtUtils {

    private final JwtConfig jwtConfig;
    private SecretKey key;

    @PostConstruct
    private void init() {
        log.debug("JWT configuration - secret: {}, expirationTime: {}",
                jwtConfig.getSecreteJwtString() != null ? "SET" : "NOT SET",
                jwtConfig.getExpirationTime());
        try {
            // 从配置读取Base64编码的密钥
            byte[] keyBytes = Base64.getDecoder().decode(jwtConfig.getSecreteJwtString());

            // 验证密钥长度
            if (keyBytes.length < 32) {
                log.warn("JWT secret is too short ({} bytes), consider using at least 32 bytes", keyBytes.length);
                // 使用密钥派生函数扩展密钥
                keyBytes = Arrays.copyOf(keyBytes, 32);
            }

            this.key = new SecretKeySpec(keyBytes, "HmacSHA256");
        } catch (IllegalArgumentException e) {
            log.error("Failed to decode JWT secret, using fallback method", e);
            // 回退方法：使用字符串直接转换
            byte[] keyBytes = Arrays.copyOf(jwtConfig.getSecreteJwtString().getBytes(StandardCharsets.UTF_8), 32);
            this.key = new SecretKeySpec(keyBytes, "HmacSHA256");
        }

        log.info("JWT configured with expiration time: {} ms", jwtConfig.getExpirationTime());

    }

    public String generateToken(String email) {
        long currentTime = System.currentTimeMillis();
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date(currentTime))
                .expiration(new Date(currentTime + jwtConfig.getExpirationTime()))
                .signWith(key)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        try {
            return extractClaims(token, Claims::getSubject);
        } catch (JwtException | IllegalArgumentException e) {
            log.warn("Invalid JWT token: {}", e.getMessage());
            return null;
        }
    }

    // 获取令牌过期时间
    public Date getExpirationDateFromToken(String token) {
        try {
            return extractClaims(token, Claims::getExpiration);
        } catch (JwtException | IllegalArgumentException e) {
            return null;
        }
    }

    // 获取令牌签发时间
    public Date getIssuedAtDateFromToken(String token) {
        try {
            return extractClaims(token, Claims::getIssuedAt);
        } catch (JwtException | IllegalArgumentException e) {
            return null;
        }
    }

    // 验证令牌是否即将过期（用于刷新）
    public boolean isTokenExpiringSoon(String token, int minutes) {
        final Date expiration = getExpirationDateFromToken(token);
        if (expiration == null) return false;

        long diff = expiration.getTime() - System.currentTimeMillis();
        return diff <= (minutes * 60 * 1000);
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsTFunction) {
        try {
            final Claims claims = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            return claimsTFunction.apply(claims);
        } catch (JwtException | IllegalArgumentException e) {
            log.warn("JWT claims extraction failed: {}", e.getMessage());
            throw e;
        }
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        try {
            final String username = getUsernameFromToken(token);
            return username != null &&
                    username.equals(userDetails.getUsername()) &&
                    !isTokenExpired(token);
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        try {
            return extractClaims(token, Claims::getExpiration).before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return true; // 如果无法解析，视为已过期
        }
    }

}
