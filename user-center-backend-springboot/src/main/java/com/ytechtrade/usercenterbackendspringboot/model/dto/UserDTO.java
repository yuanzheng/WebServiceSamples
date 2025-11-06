package com.ytechtrade.usercenterbackendspringboot.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {

    private Long id;

    private String userName;

    private String userAccount;

    private String avatarUrl;

    private Integer gender;

    @JsonIgnore
    private String userPassword;

    private String phone;

    private String email;

    private Integer userStatus;

    private Integer userRole;

    private Date createTime;

}
