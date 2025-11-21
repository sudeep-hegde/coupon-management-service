package com.monkcommerce.couponmanagementservice.rule;

import com.monkcommerce.couponmanagementservice.model.Coupon;

public class FlatDiscountRule implements RuleEvaluator {
    @Override
    public RuleResult evaluate(RuleEvaluationContext context) {

        Coupon coupon = context.coupon();

        if (!"FLAT".equalsIgnoreCase(coupon.getType().name())) {
            return new RuleResult(false, 0, "Not a flat discount coupon");
        }

        double amount = (Double) coupon.getDetails().get("amount");

        return new RuleResult(true, amount, "Flat discount applied");
    }
}
