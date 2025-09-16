package com.ytechtrade.inventorymanagementsystem.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ytechtrade.inventorymanagementsystem.services.CustomUserDetailsService;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final CustomUserDetailsService customUserDetailsService;
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = getTokenFromRequest(request);

        if (token != null) {
            try {
                String email = jwtUtils.getUsernameFromToken(token);
                if (!StringUtils.hasText(email)) {
                    handleErrorResponse(response, HttpStatus.UNAUTHORIZED, "Authentication failed, invalid user account");
                    return;
                }
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

                if (jwtUtils.isTokenValid(token, userDetails)) {
                    log.info("Valid Token, {}", email);

                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities()
                    );
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                } else {
                    log.warn("Authentication failed, invalid token");
                    // Token无效，但这里不直接报错，而是清除上下文后继续执行。
                    // 后续的FilterSecurityInterceptor会根据URL权限配置决定是否放行。
                    // 对于需要认证的URL，FilterSecurityInterceptor会抛出异常。
                    // 对于permitAll的URL，即使Token无效，也会放行。
                    SecurityContextHolder.clearContext();
                }
            } catch (JwtException | UsernameNotFoundException e) {
                log.warn("Authentication failed: {}", e.getMessage());
                // 让后续的授权组件来决定这个请求（带着一个无效的Token）是否能访问目标资源。
                SecurityContextHolder.clearContext();
            }
        }

        // 关键：无论是否有Token，无论Token是否有效，都继续执行过滤器链。
        // FilterSecurityInterceptor会最终根据URL权限配置做出决定。
        try {
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            log.error("Exception occurred in AuthFilter: " + e.getMessage());
        }

    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return null;
    }

    private void handleErrorResponse(HttpServletResponse response, HttpStatus status, String message) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(status.value());

        Map<String, Object> body = new HashMap<>();
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        body.put("timestamp", new Date());
        body.put("path", ((HttpServletRequest) response).getRequestURI());

        response.getWriter().write(objectMapper.writeValueAsString(body));
    }
}
