package com.ytechtrade.usercenterbackendspringboot.exception;

import com.ytechtrade.usercenterbackendspringboot.common.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> businessExceptionHandler(BusinessException e) {
        log.error("businessException: " + e.getMessage(), e);
        return new ResponseEntity<>(new BaseResponse<>(e.getCode(), e.getMessage(), e.getDescription()), HttpStatus.EXPECTATION_FAILED);

    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> runtimeExceptionHandler(RuntimeException e) {
        log.error("runtimeException", e);
        return new ResponseEntity<>(new BaseResponse<>(ErrorCode.SYSTEM_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
