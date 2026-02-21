package com.ytechtrade.onlineshoppingsystem.security.response;

import lombok.Data;

import java.util.List;

@Data
public class UserInfoResponse {
    private Long id;
    private String username;
    private String jwtToken;
    private String email;
    private List<String> roles;

    public UserInfoResponse(Long id, String username, String jwtToken, String email, List<String> roles) {
        this.id = id;
        this.username = username;
        this.jwtToken = jwtToken;
        this.email = email;
        this.roles = roles;
    }

    public UserInfoResponse(Long id, String username, String email, List<String> roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}
