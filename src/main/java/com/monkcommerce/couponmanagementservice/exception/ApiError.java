package com.monkcommerce.couponmanagementservice.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
public class ApiError {
    private int status;
    private String message;
    private LocalDateTime timestamp;
    private Map<String, String> validationErrors; // optional

    public ApiError(int status, String message, LocalDateTime timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }

    public ApiError(int status, String message, LocalDateTime timestamp,
                    Map<String, String> validationErrors) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
        this.validationErrors = validationErrors;
    }

}
