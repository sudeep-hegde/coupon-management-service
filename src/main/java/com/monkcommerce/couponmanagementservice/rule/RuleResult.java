package com.monkcommerce.couponmanagementservice.rule;

import com.monkcommerce.couponmanagementservice.dto.FreeItem;

import java.util.List;

public record RuleResult(
    boolean valid,
    double discount,
    String message,
    List<FreeItem> freeItems
) {
    public static RuleResult ok(double discount, String message) {
        return new RuleResult(true, discount, message, List.of());
    }

    public static RuleResult ok(boolean valid, double discount, String message) {
        return new RuleResult(valid, discount, message, List.of());
    }

    public static RuleResult fail(String message) {
        return new RuleResult(false, 0, message, List.of());
    }

    public static RuleResult okWithFreeItems(double discount, String message, List<FreeItem> items) {
        return new RuleResult(true, discount, message, items);
    }
}
