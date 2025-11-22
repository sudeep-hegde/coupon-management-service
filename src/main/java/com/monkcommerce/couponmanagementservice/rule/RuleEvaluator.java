package com.monkcommerce.couponmanagementservice.rule;

import java.util.Map;

public interface RuleEvaluator {
    String type();
    RuleResult evaluate(RuleEvaluationContext context, Map<String, Object> config);

    default boolean isValidationRule() {
        return false;
    }

    default boolean isDiscountRule() {
        return false;
    }
}
