package com.monkcommerce.couponmanagementservice.dto;

import com.monkcommerce.couponmanagementservice.rule.RuleType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Map;
import java.util.UUID;

public record CouponRuleRequest(
        @NotNull(message = "couponId is required")
        UUID couponId,

        @NotNull(message = "ruleType cannot be empty")
        RuleType ruleType,

        @NotNull(message = "ruleConfig cannot be null")
        Map<String, Object> ruleConfig,

        @NotNull(message = "priority cannot be null")
        Integer priority
) {}
