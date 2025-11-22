package com.monkcommerce.couponmanagementservice.rule;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class FlatDiscountRule implements RuleEvaluator {

    @Override
    public RuleType type() {
        return RuleType.FLAT;
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
