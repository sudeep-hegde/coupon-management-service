package com.monkcommerce.couponmanagementservice.rule;

import com.monkcommerce.couponmanagementservice.model.Coupon;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class FlatDiscountRule implements RuleEvaluator {

    @Override
    public String type() {
        return "FLAT";
    }

    @Override
    public boolean isDiscountRule() {
        return true;
    }

    @Override
    public RuleResult evaluate(RuleEvaluationContext context, Map<String,Object> config) {

        double amount = ((Number) config.get("amount")).doubleValue();
        return RuleResult.ok(true, amount, "Flat discount applied");
    }
}
