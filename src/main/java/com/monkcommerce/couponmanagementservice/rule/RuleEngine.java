package com.monkcommerce.couponmanagementservice.rule;

public interface RuleEngine {
    RuleResult evaluateRules(RuleEvaluationContext context);
}
