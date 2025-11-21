package com.monkcommerce.couponmanagementservice.rule;

import com.monkcommerce.couponmanagementservice.dto.CartItemDto;
import com.monkcommerce.couponmanagementservice.model.Coupon;
import com.monkcommerce.couponmanagementservice.model.CouponRule;

import java.util.List;

public record RuleEvaluationContext(
    Coupon coupon,
    List<CouponRule> rules,
    List<CartItemDto> items,
    String userId
) {}
