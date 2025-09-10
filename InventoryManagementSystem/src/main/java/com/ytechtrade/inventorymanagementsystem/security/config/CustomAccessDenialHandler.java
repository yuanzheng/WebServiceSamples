package com.ytechtrade.inventorymanagementsystem.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ytechtrade.inventorymanagementsystem.models.dtos.Response;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAccessDenialHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException)
            throws IOException, ServletException {

        /** TODO 生产环境中使用通用消息，避免暴露权限规则细节
         String errorMessage = "权限不足，无法访问该资源";
         开发环境可以返回详细错误信息
         if (Arrays.asList(environment.getActiveProfiles()).contains("dev")) {
         errorMessage = accessDeniedException.getMessage();
         }
         */
        Response errorResponse = Response.builder()
                .status(HttpStatus.FORBIDDEN.value())
                .message(accessDeniedException.getMessage())
                .build();

        response.setContentType("application/json");
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));

        /**
         TODO logger.warn("Access is refused: {} - {}", request.getRequestURI(),
         accessDeniedException.getMessage());
         */
    }
}
