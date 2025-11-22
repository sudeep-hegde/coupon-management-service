package com.monkcommerce.couponmanagementservice.dto;

import com.monkcommerce.couponmanagementservice.model.CouponType;
import com.monkcommerce.couponmanagementservice.model.StackingType;
import com.monkcommerce.couponmanagementservice.validator.annotation.ValidDateRange;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@ValidDateRange
public record CouponRequestDto(
        @NotBlank(message = "Coupon code cannot be empty")
        @Size(max = 50, message = "Coupon code cannot exceed 50 characters")
        String code,

        @NotNull(message = "Coupon type is required")
        CouponType type,

        @NotNull(message = "Start date is required")
        LocalDateTime startDate,

        @NotNull(message = "End date is required")
        LocalDateTime endDate,

        @NotNull(message = "maxUses is required")
        @Positive(message = "maxUses must be greater than 0")
        Integer maxUses,

        @NotNull(message = "maxUses is required")
        @Positive(message = "maxUses must be greater than 0")
        Integer perUserLimit,

        @NotNull(message = "Coupon type is required")
        StackingType stackingType,

        @NotNull(message = "active flag is required")
        Boolean active
) {}
