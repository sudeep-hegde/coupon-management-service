package com.monkcommerce.couponmanagementservice.dto;

import com.monkcommerce.couponmanagementservice.model.CouponType;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public record CouponResponse(
        UUID id,
        String code,
        CouponType type,
        Map<String, Object> details,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Integer maxUses,
//        Integer uses,
//        Integer repetitionLimit,
        Boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
