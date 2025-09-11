package com.ytechtrade.inventorymanagementsystem.security.config;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import java.util.concurrent.TimeUnit;

@Configuration
@ConfigurationProperties(prefix = "jwt")
@Data
@Validated
public class JwtConfig {
    @NotBlank
    private String secreteJwtString;

    @Min(60000) // 最少1分钟
    private long expirationTime = TimeUnit.DAYS.toMillis(1);
}
