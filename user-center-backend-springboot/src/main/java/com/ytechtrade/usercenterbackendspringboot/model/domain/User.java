package com.ytechtrade.usercenterbackendspringboot.model.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@TableName("user")
@Data
public class User implements Serializable {
    /**
     * Primary Key ID
     */
    @TableId(type = IdType.AUTO)
    @TableField("id")
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

    public Long getId() {
        return id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public Integer getGender() {
        return gender;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }



}
