package com.ytechtrade.usercenterbackendspringboot.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    SUCCESS(0, "ok", ""),
    PARAMS_ERROR(40000, "Request parameter error", ""),
    NULL_ERROR(40001, "Request data is empty", ""),
    NOT_LOGIN(40100, "Not logged in", ""),
    NO_AUTH(40101, "No permission", ""),
    SYSTEM_ERROR(50000, "Internal system exception", ""),
    USER_ERROR(60000, "User data error", "");

    private final int code;

    /**
     * 状态码信息
     */
    private final String message;

    /**
     * 状态码描述（详情）
     */
    private final String description;

    ErrorCode(int code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }

}
