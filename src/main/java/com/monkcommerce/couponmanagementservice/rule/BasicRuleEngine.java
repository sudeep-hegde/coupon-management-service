package com.monkcommerce.couponmanagementservice.rule;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BasicRuleEngine implements RuleEngine {

    private final List<RuleEvaluator> rules = List.of(
            new PercentageDiscountRule(),
            new FlatDiscountRule()
            // add more rules later
    );

    @Override
    public RuleResult evaluateRules(RuleEvaluationContext context) {
        for (RuleEvaluator rule : rules) {
            RuleResult result = rule.evaluate(context);

            if (result.valid()) {
                return result; // First matching rule applies
            }
        }

        return new RuleResult(false, 0, "No applicable coupon rule found");
    }
}
