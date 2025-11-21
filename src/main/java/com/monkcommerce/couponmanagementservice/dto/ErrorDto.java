package com.monkcommerce.couponmanagementservice.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorDto {
    private String message;
    private String requestId;
    private LocalDateTime dateTime;
}
