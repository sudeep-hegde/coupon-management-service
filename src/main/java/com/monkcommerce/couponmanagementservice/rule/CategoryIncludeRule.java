package com.monkcommerce.couponmanagementservice.rule;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class CategoryIncludeRule implements RuleEvaluator {
    @Override
    public RuleType type() {
        return RuleType.CATEGORY_INCLUDE;
    }

    @Override
    public boolean isValidationRule() {
        return true; // this is NOT a discount rule
    }

    @Override
    public RuleResult evaluate(RuleEvaluationContext ctx, Map<String, Object> config) {

        List<String> allowed = (List<String>) config.get("categories");

        boolean ok = ctx.items().stream()
                .allMatch(i -> allowed.contains(i.category()));

        if (!ok) {
            return RuleResult.fail("Cart contains items outside allowed categories: " + allowed);
        }

        return RuleResult.ok(true, 0, "Category include rule passed");
    }
}
