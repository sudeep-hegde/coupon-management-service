package com.monkcommerce.couponmanagementservice.rule;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class RuleRegistry {
    private final Map<String, RuleEvaluator> registry = new HashMap<>();

    public RuleRegistry(List<RuleEvaluator> evaluators) {
        evaluators.forEach(e -> registry.put(e.type(), e));
    }

    public RuleEvaluator get(String type) {
        return registry.get(type);
    }
}
