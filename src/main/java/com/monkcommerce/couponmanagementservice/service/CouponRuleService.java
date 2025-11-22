package com.monkcommerce.couponmanagementservice.service;

import com.monkcommerce.couponmanagementservice.dto.CouponRuleRequest;
import com.monkcommerce.couponmanagementservice.dto.CouponRuleResponse;

import java.util.List;
import java.util.UUID;

public interface CouponRuleService {
    CouponRuleResponse createRule(CouponRuleRequest request);

    List<CouponRuleResponse> getRulesByCoupon(UUID couponId);

    CouponRuleResponse updateRule(UUID ruleId, CouponRuleRequest request);

    void deleteRule(UUID ruleId);
}
