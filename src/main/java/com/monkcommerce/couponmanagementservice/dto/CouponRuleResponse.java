package com.monkcommerce.couponmanagementservice.dto;

import com.monkcommerce.couponmanagementservice.rule.RuleType;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public record CouponRuleResponse(
        UUID id,
        UUID couponId,
        RuleType ruleType,
        Map<String, Object> ruleConfig,
        Integer priority,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
