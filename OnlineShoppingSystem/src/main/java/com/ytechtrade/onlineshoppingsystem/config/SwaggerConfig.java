package com.ytechtrade.onlineshoppingsystem.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        /* 定义了一个 Bearer Token 的安全方案
         * 告诉 Swagger UI，我们要使用 JWT Token，我们要使用 HTTP 授权标头中传递的 token。
         * 这意味着 Swagger UI 中会有一个“授权”按钮。
         * 这是一个基于 HTTP 的方案。
         */
        SecurityScheme bearerScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .description("JWT Bearer Token");

        /* 告诉 Swagger，上述安全方案是所有 API 调用所必需的 */
        SecurityRequirement bearerRequirement = new SecurityRequirement()
                .addList("Bearer Authentication");

        /* 定义用于 Swagger UI 生成的实际 OpenAPI 对象 */
        return new OpenAPI()
                .info(new Info()
                        .title("Spring Boot eCommerce API")
                        .version("1.0")
                        .description("This is a Spring Boot Project for eCommerce")
                        .license(new License().name("Our License Link").url("http://www.ytechtrade.top"))
                        .contact(new Contact()
                                .name("Lucas Song")
                                .email("song_yuanzheng@ytechtrade.top")
                                .url("https://github.com/yuanzheng/WebServiceSamples/tree/online-shopping-springboot")))
                .externalDocs(new ExternalDocumentation()
                        .description("Project Documentation")
                        .url("http://www.ytechtrade.top"))
                .components(new Components()
                        .addSecuritySchemes("Bearer Authentication", bearerScheme))
                .addSecurityItem(bearerRequirement);
    }
}
