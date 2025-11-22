package com.monkcommerce.couponmanagementservice.rule;

import com.monkcommerce.couponmanagementservice.model.CouponRule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BasicRuleEngine implements RuleEngine {

    //fetch from DB.
//    private final List<RuleEvaluator> rules = List.of(
//            new PercentageDiscountRule(),
//            new FlatDiscountRule()
//            // add more rules later
//    );
    private final RuleRegistry ruleRegistry;

    @Override
    public RuleResult evaluateRules(RuleEvaluationContext context) {

        // validation rules first
        for (CouponRule rule : context.rules()) {

            RuleEvaluator evaluator = ruleRegistry.get(rule.getRuleType());

            if (evaluator.isValidationRule()) {
                RuleResult result = evaluator.evaluate(context, rule.getRuleConfig());

                if (!result.valid()) {
                    return result;
                }
            }
        }
        // then discount rule.
        for (CouponRule rule : context.rules()) {

            RuleEvaluator evaluator = ruleRegistry.get(rule.getRuleType());

            if (evaluator.isDiscountRule()) {

                RuleResult result = evaluator.evaluate(context, rule.getRuleConfig());

                if (result.valid()) {  // && result.discount() > 0
                    return result; // return first/priority discount rule.
                }
            }
        }

        return RuleResult.ok(true, 0, "Coupon applied. No discount rule matched.");
    }
}
