package com.guanwx.exception;

import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 自定义业务异常
 */
@Data
public class GlobalException extends RuntimeException {

    private final HttpStatus status;

    public GlobalException(String message) {
        super(message);
        this.status = HttpStatus.BAD_REQUEST;
    }

    public GlobalException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

}
