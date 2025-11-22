package com.monkcommerce.couponmanagementservice.mapper;

import com.monkcommerce.couponmanagementservice.dto.CouponRuleRequest;
import com.monkcommerce.couponmanagementservice.dto.CouponRuleResponse;
import com.monkcommerce.couponmanagementservice.model.Coupon;
import com.monkcommerce.couponmanagementservice.model.CouponRule;

public class CouponRuleMapper {
    public static CouponRule toEntity(CouponRuleRequest dto, Coupon coupon) {
        return CouponRule.builder()
                .id(null)
                .coupon(coupon)
                .ruleType(dto.ruleType())
                .ruleConfig(dto.ruleConfig())
                .priority(dto.priority())
                .build();
    }

    public static CouponRuleResponse toResponse(CouponRule rule) {
        return new CouponRuleResponse(
                rule.getId(),
                rule.getCoupon().getId(),
                rule.getRuleType(),
                rule.getRuleConfig(),
                rule.getPriority(),
                rule.getCreatedAt(),
                rule.getUpdatedAt()
        );
    }
}
