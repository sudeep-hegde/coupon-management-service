package com.monkcommerce.couponmanagementservice.rule;

import com.monkcommerce.couponmanagementservice.model.Coupon;

public class PercentageDiscountRule implements RuleEvaluator {
    @Override
    public RuleResult evaluate(RuleEvaluationContext context) {
        Coupon coupon = context.coupon();

        if (!"PERCENTAGE".equalsIgnoreCase(coupon.getType().name())) {
            return new RuleResult(false, 0, "Not a percentage coupon");
        }

        double total = context.items().stream()
                .mapToDouble(i -> i.price() * i.quantity())
                .sum();

        double percent = (Double) coupon.getDetails().get("percent");
        double discount = total * percent / 100;

        return new RuleResult(true, discount, "Percentage coupon applied");
    }
}
