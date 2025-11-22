package com.monkcommerce.couponmanagementservice.dto;

import com.monkcommerce.couponmanagementservice.model.CouponType;
import com.monkcommerce.couponmanagementservice.model.StackingType;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public record CouponResponse(
        UUID id,
        String code,
        CouponType type,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Integer maxUses,
        Integer perUserLimit,
        StackingType stackingType,
        Boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
