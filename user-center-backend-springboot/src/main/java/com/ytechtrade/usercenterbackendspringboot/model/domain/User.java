package com.ytechtrade.usercenterbackendspringboot.model.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
@TableName("user")
public class User implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * Primary Key ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * Users Name
     */
    @TableField("user_name")
    private String userName;

    /**
     * Users account number
     */
    @TableField("user_account")
    private String userAccount;

    /**
     * Users profile picture
     */
    @TableField("avatar_url")
    private String avatarUrl;

    /**
     * Gender
     */
    @TableField("gender")
    private Integer gender;

    /**
     * Login password
     */
    @TableField("user_password")
    private String userPassword;

    /**
     * Users phone number
     */
    @TableField("phone")
    private String phone;

    /**
     * Email
     */
    @TableField("email")
    private String email;

    /**
     * Status 0 - normal status
     */
    @TableField("user_status")
    private Integer userStatus;

    /**
     * Current time
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * Time to update the user
     */
    @TableField("update_time")
    private Date updateTime;

    /**
     * If it is required to delete
     */
    @TableLogic
    @TableField("is_delete")
    private Integer isDelete;

    /**
     * 0 - normal user, 1 - Admin
     */
    @TableField("role")
    private Integer userRole;

}
