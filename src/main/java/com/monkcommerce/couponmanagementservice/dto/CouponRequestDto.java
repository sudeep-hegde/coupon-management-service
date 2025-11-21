package com.monkcommerce.couponmanagementservice.dto;

import com.monkcommerce.couponmanagementservice.model.CouponType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Map;

public record CouponRequestDto(
        @NotBlank(message = "Coupon code cannot be empty")
        @Size(max = 50, message = "Coupon code cannot exceed 50 characters")
        String code,
        @NotNull(message = "Coupon type is required")
        CouponType type,
        @NotNull(message = "Coupon details cannot be null")
        Map<String, Object> details,
        @NotNull(message = "Start date is required")
        LocalDateTime startDate,
        @NotNull(message = "End date is required")
        LocalDateTime endDate,
        @NotNull(message = "maxUses is required")
        @Positive(message = "maxUses must be greater than 0")
        Integer maxUses,
        @NotNull(message = "repetitionLimit is required")
        @Positive(message = "repetitionLimit must be greater than 0")
        Integer repetitionLimit,
        @NotNull(message = "active flag is required")
        Boolean active
) {}
