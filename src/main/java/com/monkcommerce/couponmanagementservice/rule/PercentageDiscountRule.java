package com.monkcommerce.couponmanagementservice.rule;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PercentageDiscountRule implements RuleEvaluator {

    @Override
    public RuleType type() { return RuleType.PERCENTAGE; }

    @Override
    public boolean isDiscountRule() {
        return true;
    }

    @Override
    public RuleResult evaluate(RuleEvaluationContext context, Map<String, Object> config) {
        double percent = ((Number) config.get("percent")).doubleValue();
        double max = config.containsKey("maxDiscount")
                ? ((Number) config.get("maxDiscount")).doubleValue()
                : Double.MAX_VALUE;

        double total = context.items().stream()
                .mapToDouble(i -> i.price() * i.quantity())
                .sum();

        double discount = total * percent / 100;
        discount = Math.min(discount, max);

        return RuleResult.ok(true, discount, "Percentage discount applied");
    }
}
