package com.monkcommerce.couponmanagementservice.rule;

public interface RuleEvaluator {
    RuleResult evaluate(RuleEvaluationContext context);
}
